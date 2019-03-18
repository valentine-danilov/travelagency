package com.epam.travelagency.repository.specification.impl.postgre.tour;

import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.repository.specification.TourSpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ByHotelStars implements TourSpecification {

    private Short stars;
    private String operation;

    public ByHotelStars(Short stars, String operation) {
        this.stars = stars;
        this.operation = operation;
    }

    @Override
    public Predicate toPredicate(Root<Tour> root, CriteriaBuilder builder) {
        if (operation.equals("<".trim())) {
            return builder.lessThanOrEqualTo(root.get("hotel").get("stars"), stars);
        } else if (operation.equals(">".trim())) {
            return builder.greaterThanOrEqualTo(root.get("hotel").get("stars"), stars);
        } else if (operation.equals("=".trim())) {
            return builder.equal(root.get("hotel").get("stars"), stars);
        }
        throw new IllegalArgumentException("Operation '" + operation + "' is not supported");
    }
}
