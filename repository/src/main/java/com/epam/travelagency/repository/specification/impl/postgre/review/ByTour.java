package com.epam.travelagency.repository.specification.impl.postgre.review;

import com.epam.travelagency.entity.Review;
import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.repository.specification.ReviewSpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ByTour implements ReviewSpecification {

    private Tour tour;

    public ByTour(Tour tour) {
        this.tour = tour;
    }

    @Override
    public Predicate toPredicate(Root<Review> root, CriteriaBuilder builder) {
        return builder.equal(root.get("tour"), tour);
    }
}
