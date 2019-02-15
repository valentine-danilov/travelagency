package com.epam.travelagency.bean;

import com.epam.travelagency.bean.Tour;
import com.epam.travelagency.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Review extends AbstractEntity {
    private Date date;
    private String[] text;
    private User user;
    private Tour tour;

    public Review() {
        date = new Date();
        text = new String[1];
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String[] getText() {
        return text;
    }

    public void setText(String[] text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    @Autowired
    public void setUser(User user) {
        this.user = user;
    }

    public Tour getTour() {
        return tour;
    }

    @Autowired
    public void setTour(Tour tour) {
        this.tour = tour;
    }
}
