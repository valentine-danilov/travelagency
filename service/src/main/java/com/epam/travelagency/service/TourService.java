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

    public List<Tour> getAllWithOffsetAndMaxSize(Integer offset, Integer maxResult) {
        return repository.getAllWithOffsetAndMaxSize(offset, maxResult);
    }

    @Transactional
    public Tour readById(Integer id) {
        return repository.get(id);
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

    public List<Tour> findAllBySpecification(List<TourSpecification> specifications) {
        return repository.findAllBySpecification(specifications);
    }

    public List<Tour> findAllBySpecificationWithOffsetAndMaxSize
            (List<TourSpecification> specifications,
             Integer offset,
             Integer maxResult) {
        return repository
                .findAllBySpecificationWithOffsetAndMaxSize
                        (specifications, offset, maxResult);
    }

    public List<Tour> findAllByUser(Integer id) {
        return repository.findAllByUser(id);
    }

    public Long getPageNumber(Integer pageSize, List<TourSpecification> specifications) {
        Long dataSize = repository.getPageNumber(specifications);
        if (dataSize % 2 == 0) {
            return dataSize / pageSize;
        } else return dataSize / pageSize + 1;
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
        if (photo != null) {
            tour.setPhoto(photo);
        }
        tour.setDate(date);
        tour.setDuration(duration);
        tour.setCost(cost);
        tour.setTourType(tourType);
        tour.setHotel(hotel);
        tour.setCountry(country);
        if (description != null) {
            tour.setDescription(description);
        }
        return tour;
    }

}
