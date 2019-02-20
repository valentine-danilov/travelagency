package com.epam.travelagency.bean;

import com.epam.travelagency.bean.enumeration.TourType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Component
public class Tour extends AbstractEntity {
    private String photo;
    private String date;
    private Integer duration;
    private String description;
    private BigDecimal cost;
    private TourType tourType;
    private Hotel hotel;
    private Country country;

    public Tour() {
        photo = "";
        date = "";
        duration = 0;
        description = "";
        cost = new BigDecimal(0);
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public TourType getTourType() {
        return tourType;
    }

    public void setTourType(TourType tourType) {
        this.tourType = tourType;
    }

    public Hotel getHotel() {
        return hotel;
    }

    @Autowired
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Country getCountry() {
        return country;
    }

    @Autowired
    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", photo='" + photo + '\'' +
                ", date=" + date +
                ", duration=" + duration +
                ", description=" + description +
                ", cost=" + cost +
                ", tourType=" + tourType +
                ", hotel=" + hotel +
                ", country=" + country +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tour tour = (Tour) o;
        return Objects.equals(photo, tour.photo) &&
                Objects.equals(date, tour.date) &&
                Objects.equals(duration, tour.duration) &&
                Objects.equals(description, tour.description) &&
                Objects.equals(cost, tour.cost) &&
                tourType == tour.tourType &&
                Objects.equals(hotel, tour.hotel) &&
                Objects.equals(country, tour.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(photo, date, duration, description, cost, tourType, hotel, country);
    }
}
