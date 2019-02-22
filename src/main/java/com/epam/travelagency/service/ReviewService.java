package com.epam.travelagency.service;

import com.epam.travelagency.bean.Review;
import com.epam.travelagency.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository repository;

    @Autowired
    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public List<Review> readAll() {
        return repository.read();
    }

    public Review readById(Integer id) {
        return repository.read(id);
    }

    public void deleteById(Integer id) {
        repository.delete(id);
    }

    public void add(String date,
                    String text,
                    Integer userId,
                    Integer tourId) {
        Review tour = constructTour(
                null, date, text,
                userId, tourId
        );
        repository.create(tour);
    }

    public void update(Integer id,
                       String date,
                       String text,
                       Integer userId,
                       Integer tourId) {
        Review tour = constructTour(
                id, date, text,
                userId, tourId
        );
        repository.update(tour);
    }

    private Review constructTour(Integer id,
                                 String date,
                                 String text,
                                 Integer userId,
                                 Integer tourId) {
        Review review = new Review();
        if (id != null) {
            review.setId(id);
        }
        review.setDate(date);
        review.setText(text);
        review.setUserId(userId);
        review.setTourId(tourId);
        return review;
    }
}
