package com.epam.travelagency.service;

import com.epam.travelagency.bean.Hotel;
import com.epam.travelagency.bean.enumeration.HotelFeature;
import com.epam.travelagency.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class HotelService {

    private final HotelRepository repository;

    @Autowired
    public HotelService(HotelRepository repository) {
        this.repository = repository;
    }

    public List<Hotel> readAll() {
        return repository.read();
    }

    public Hotel readById(Integer id) {
        return repository.read(id);
    }

    public void update(Integer id,
                       String name,
                       Byte stars,
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

    public void deleteById(Integer id) {
        repository.delete(id);
    }

    public void add(String name,
                    Byte stars,
                    String website,
                    BigDecimal latitude,
                    BigDecimal longitude,
                    HotelFeature feature) {
        Hotel hotel = constructHotel(
                null, name, stars, website,
                latitude, longitude, feature
        );
        repository.create(hotel);
    }

    private Hotel constructHotel(Integer id,
                                 String name,
                                 Byte stars,
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
