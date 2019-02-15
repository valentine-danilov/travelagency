package com.epam.travelagency.bean;

import com.epam.travelagency.bean.enumeration.TourType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

@Component
public class Tour extends AbstractEntity {
    private String photo;
    private Date date;
    private Integer duration;
    private String[] description;
    private BigDecimal cost;
    private TourType tourType;
    private Hotel hotel;
    private Country country;

    public Tour() {
        photo = "";
        date = new Date();
        duration = 0;
        description = new String[1];
        cost = new BigDecimal(0);
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String[] getDescription() {
        return description;
    }

    public void setDescription(String[] description) {
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
                ", description=" + Arrays.toString(description) +
                ", cost=" + cost +
                ", tourType=" + tourType +
                ", hotel=" + hotel +
                ", country=" + country +
                '}';
    }
}
