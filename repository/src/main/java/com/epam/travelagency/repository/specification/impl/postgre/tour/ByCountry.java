package com.epam.travelagency.repository.specification.impl.postgre.tour;

import com.epam.travelagency.entity.Country;
import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.repository.specification.TourSpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ByCountry implements TourSpecification {

    private Country country;

    public ByCountry(Country country) {
        this.country = country;
    }

    @Override
    public Predicate toPredicate(Root<Tour> root, CriteriaBuilder builder) {
        return builder.equal(root.get("country"), country);
    }
}
