package com.epam.travelagency.service;

import com.epam.travelagency.entity.Country;
import com.epam.travelagency.entity.Hotel;
import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.enumeration.TourType;
import com.epam.travelagency.repository.IRepository;
import com.epam.travelagency.repository.ITourRepository;
import com.epam.travelagency.repository.impl.postgre.TourRepository;
import com.epam.travelagency.repository.specification.TourSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class TourService {

    private final
    ITourRepository repository;

    @Autowired
    public TourService(ITourRepository repository) {
        this.repository = repository;
    }

    public List<Tour> readAll() {
        return repository.getAll();
    }

    public Tour readById(Integer id) {
        return repository.get(id);
    }

    public List<Tour> searchTour(TourSpecification specification) {
        return repository.findAllBySpecification(specification);
    }

    public void deleteById(Integer id) {
        repository.delete(id);
    }


    @Transactional
    public void add(String photo,
                    Date date,
                    Integer duration,
                    String description,
                    BigDecimal cost,
                    TourType tourType,
                    Hotel hotel,
                    Country country) {
        Tour tour = constructTour(
                null, photo, date, duration, description, cost,
                tourType, hotel, country
        );
        repository.add(tour);
    }

    public void update(Integer id,
                       String photo,
                       Date date,
                       Integer duration,
                       String description,
                       BigDecimal cost,
                       TourType tourType,
                       Hotel hotel,
                       Country country) {
        Tour tour = constructTour(
                id, photo, date, duration, description, cost,
                tourType, hotel, country
        );
        repository.update(tour);
    }

    private Tour constructTour(Integer id,
                               String photo,
                               Date date,
                               Integer duration,
                               String description,
                               BigDecimal cost,
                               TourType tourType,
                               Hotel hotel,
                               Country country) {
        Tour tour = new Tour();
        if (id != null) {
            tour.setId(id);
        }
        tour.setPhoto(photo);
        tour.setDate(date);
        tour.setDuration(duration);
        tour.setCost(cost);
        tour.setTourType(tourType);
        tour.setHotel(hotel);
        tour.setCountry(country);
        tour.setDescription(description);
        return tour;
    }

}
