package com.epam.travelagency.repository.specification.impl.postgre.tour;

import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.repository.specification.TourSpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

public class ByTourDate implements TourSpecification {

    private Date date;

    public ByTourDate(String date) {
        this.date = java.sql.Date.valueOf(date);
    }

    @Override
    public Predicate toPredicate(Root<Tour> root, CriteriaBuilder builder) {
        return builder.equal(root.get("date"), date);
    }
}
