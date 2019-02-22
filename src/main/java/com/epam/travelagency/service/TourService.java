package com.epam.travelagency.service;

import com.epam.travelagency.bean.Tour;
import com.epam.travelagency.bean.enumeration.TourType;
import com.epam.travelagency.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TourService {

    private final TourRepository repository;

    @Autowired
    public TourService(TourRepository repository) {
        this.repository = repository;
    }

    public List<Tour> readAll() {
        return repository.read();
    }

    public Tour readById(Integer id) {
        return repository.read(id);
    }

    public void deleteById(Integer id) {
        repository.delete(id);
    }

    public void add(String photo,
                    String date,
                    Integer duration,
                    BigDecimal cost,
                    TourType tourType,
                    Integer hotelId,
                    Integer countryId) {
        Tour tour = constructTour(
                null, photo, date, duration, cost,
                tourType, hotelId, countryId
        );
        repository.create(tour);
    }

    public void update(Integer id,
                       String photo,
                       String date,
                       Integer duration,
                       BigDecimal cost,
                       TourType tourType,
                       Integer hotelId,
                       Integer countryId) {
        Tour tour = constructTour(
                id, photo, date, duration, cost,
                tourType, hotelId, countryId
        );
        repository.update(tour);
    }

    private Tour constructTour(Integer id,
                               String photo,
                               String date,
                               Integer duration,
                               BigDecimal cost,
                               TourType tourType,
                               Integer hotelId,
                               Integer countryId) {
        Tour tour = new Tour();
        if (id != null) {
            tour.setId(id);
        }
        tour.setPhoto(photo);
        tour.setDate(date);
        tour.setDuration(duration);
        tour.setCost(cost);
        tour.setTourType(tourType);
        tour.setHotelId(hotelId);
        tour.setCountryId(countryId);
        return tour;
    }
}
