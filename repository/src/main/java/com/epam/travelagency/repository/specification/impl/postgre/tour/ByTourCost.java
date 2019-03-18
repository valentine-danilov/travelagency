package com.epam.travelagency.repository.specification.impl.postgre.tour;

import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.repository.specification.TourSpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

public class ByTourCost implements TourSpecification {

    private BigDecimal cost;
    private String operation;

    public ByTourCost(BigDecimal cost, String operation) {
        this.cost = cost;
        this.operation = operation;
    }

    @Override
    public Predicate toPredicate(Root<Tour> root, CriteriaBuilder builder) {
        if (operation.equals("<".trim())) {
            return builder.lessThanOrEqualTo(root.get("cost"), cost);
        } else if (operation.equals(">".trim())) {
            return builder.greaterThanOrEqualTo(root.get("cost"), cost);
        } else if (operation.equals("=".trim())) {
            return builder.equal(root.get("cost"), cost);
        }
        throw new IllegalArgumentException("Operation '" + operation + "' is not supported");
    }
}
