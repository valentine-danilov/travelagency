package com.epam.travelagency.repository.specification.impl.postgre.tour;

import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.enumeration.TourType;
import com.epam.travelagency.repository.specification.TourSpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ByTourType implements TourSpecification {

    private TourType tourType;

    public ByTourType(TourType tourType) {
        this.tourType = tourType;
    }

    @Override
    public Predicate toPredicate(Root<Tour> root, CriteriaBuilder builder) {
        return builder.equal(root.get("tourType"), tourType);
    }
}
