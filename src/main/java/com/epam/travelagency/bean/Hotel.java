package com.epam.travelagency.bean;

import com.epam.travelagency.bean.enumeration.HotelFeature;

import java.math.BigDecimal;
import java.util.Objects;

public class Hotel extends AbstractEntity{
    private String name;
    private Byte stars;
    private String website;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private HotelFeature features;

    public Hotel() {
        name = "";
        stars = 0;
        website = "";
        latitude = new BigDecimal(0);
        latitude = new BigDecimal(0);
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

    public HotelFeature getFeatures() {
        return features;
    }

    public void setFeatures(HotelFeature features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", stars=" + stars +
                ", website='" + website + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", features=" + features +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(name, hotel.name) &&
                Objects.equals(stars, hotel.stars) &&
                Objects.equals(website, hotel.website) &&
                Objects.equals(latitude, hotel.latitude) &&
                Objects.equals(longitude, hotel.longitude) &&
                features == hotel.features;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, stars, website, latitude, longitude, features);
    }
}
