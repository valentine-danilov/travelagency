package com.epam.travelagency.service;

import com.epam.travelagency.entity.Hotel;
import com.epam.travelagency.enumeration.HotelFeature;
import com.epam.travelagency.repository.IHotelRepository;
import com.epam.travelagency.repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class HotelService {

    private final
    IHotelRepository repository;

    @Autowired
    public HotelService(IHotelRepository repository) {
        this.repository = repository;
    }


    public List<Hotel> readAll() {
        return repository.getAll();
    }

    public Hotel readById(Integer id) {
        return repository.get(id);
    }

    public void deleteById(Integer id) {
        repository.delete(id);
    }

    @Transactional
    public void add(String name,
                    Short stars,
                    String website,
                    BigDecimal latitude,
                    BigDecimal longitude,
                    HotelFeature feature) {
        Hotel hotel = constructHotel(
                null, name, stars, website,
                latitude, longitude, feature
        );
        repository.add(hotel);
    }

    public void update(Integer id,
                       String name,
                       Short stars,
                       String website,
                       BigDecimal latitude,
                       BigDecimal longitude,
                       HotelFeature feature) {
        Hotel hotel = constructHotel(
                id, name, stars, website,
                latitude, longitude, feature
        );
        repository.update(hotel);
    }


    private Hotel constructHotel(Integer id,
                                 String name,
                                 Short stars,
                                 String website,
                                 BigDecimal latitude,
                                 BigDecimal longitude,
                                 HotelFeature feature) {
        Hotel hotel = new Hotel();
        if (id != null) {
            hotel.setId(id);
        }
        hotel.setName(name);
        hotel.setStars(stars);
        hotel.setWebsite(website);
        hotel.setLatitude(latitude);
        hotel.setLongitude(longitude);
        hotel.setFeatures(feature);
        return hotel;
    }
}
