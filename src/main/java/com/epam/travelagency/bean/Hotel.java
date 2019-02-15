package com.epam.travelagency.bean;

import com.epam.travelagency.bean.enumeration.HotelFeatures;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class Hotel extends AbstractEntity{
    private String name;
    private Byte stars;
    private String website;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private List<HotelFeatures> hotelFeatures;

    public Hotel() {
        name = "";
        stars = 0;
        website = "";
        latitude = new BigDecimal(0);
        latitude = new BigDecimal(0);
        hotelFeatures = new ArrayList<HotelFeatures>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getStars() {
        return stars;
    }

    public void setStars(Byte stars) {
        this.stars = stars;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public List<HotelFeatures> getHotelFeatures() {
        return hotelFeatures;
    }

    public void setHotelFeatures(List<HotelFeatures> hotelFeatures) {
        this.hotelFeatures = hotelFeatures;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", stars=" + stars +
                ", website='" + website + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", hotelFeatures=" + hotelFeatures +
                '}';
    }
}
