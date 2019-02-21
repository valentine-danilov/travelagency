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
    private Integer countryId;
    private Integer hotelId;

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

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "photo='" + photo + '\'' +
                ", date='" + date + '\'' +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", tourType=" + tourType +
                ", countryId=" + countryId +
                ", hotelId=" + hotelId +
                ", id=" + id +
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
                Objects.equals(countryId, tour.countryId) &&
                Objects.equals(hotelId, tour.hotelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(photo, date, duration, description, cost, tourType, countryId, hotelId);
    }
}
