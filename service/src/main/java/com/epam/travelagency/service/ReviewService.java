package com.epam.travelagency.service;

import com.epam.travelagency.entity.Review;
import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.entity.User;
import com.epam.travelagency.repository.IRepository;
import com.epam.travelagency.repository.IReviewRepository;
import com.epam.travelagency.repository.specification.ReviewSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ReviewService {

    private static Logger LOG = LoggerFactory.getLogger(ReviewService.class);

    private final
    IReviewRepository repository;

    @Autowired
    public ReviewService(IReviewRepository repository) {
        this.repository = repository;
    }


    public List<Review> readAll() {
        return repository.getAll();
    }

    public Review readById(Integer id) {
        return repository.get(id);
    }

    public void deleteById(Integer id) {
        repository.delete(id);
    }

    public void add(Date date,
                    String text,
                    User user,
                    Tour tour) {
        Review review = constructTour(
                null, date, text,
                user, tour
        );
        repository.add(review);
    }

    public void update(Integer id,
                       Date date,
                       String text,
                       User user,
                       Tour tour) {
        Review review = constructTour(
                id, date, text,
                user, tour
        );
        repository.update(review);
    }

    public List<Review> findAllBySpecification(List<ReviewSpecification> specifications) {
        return repository.findAllBySpecification(specifications);
    }

    private Review constructTour(Integer id,
                                 Date date,
                                 String text,
                                 User user,
                                 Tour tour) {
        Review review = new Review();
        if (id != null) {
            review.setId(id);
        }
        review.setDate(date);
        review.setText(text);
        review.setUser(user);
        review.setTour(tour);
        return review;
    }
}
