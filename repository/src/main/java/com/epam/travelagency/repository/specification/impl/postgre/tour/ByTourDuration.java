package com.epam.travelagency.repository.specification.impl.postgre.tour;

import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.repository.specification.TourSpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ByTourDuration implements TourSpecification {

    private Integer duration;
    private String operation;

    public ByTourDuration(Integer duration, String operation) {
        this.duration = duration;
        this.operation = operation;
    }

    @Override
    public Predicate toPredicate(Root<Tour> root, CriteriaBuilder builder) {
        if (operation.equals("<".trim())) {
            return builder.lessThanOrEqualTo(root.get("duration"), duration);
        } else if (operation.equals(">".trim())) {
            return builder.greaterThanOrEqualTo(root.get("duration"), duration);
        } else if (operation.equals("=".trim())) {
            return builder.equal(root.get("duration"), duration);
        }
        throw new IllegalArgumentException("Operation '" + operation + "' is not supported");
    }
}
