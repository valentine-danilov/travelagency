package com.epam.travelagency.bean;

import java.util.Objects;

public class Review extends AbstractEntity {
    private String date;
    private String text;
    private Integer userId;
    private Integer tourId;

    public Review() {
        date = "";
        text = "";
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    @Override
    public String toString() {
        return "Review{" +
                "date='" + date + '\'' +
                ", text='" + text + '\'' +
                ", userId=" + userId +
                ", tourId=" + tourId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(date, review.date) &&
                Objects.equals(text, review.text) &&
                Objects.equals(userId, review.userId) &&
                Objects.equals(tourId, review.tourId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, text, userId, tourId);
    }
}
