package com.epam.travelagency.repository.specification.impl.postgre.review;

import com.epam.travelagency.entity.Review;
import com.epam.travelagency.entity.User;
import com.epam.travelagency.repository.specification.ReviewSpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ByUserId implements ReviewSpecification {
    private Integer id;

    public ByUserId(Integer id) {
        this.id = id;
    }

    @Override
    public Predicate toPredicate(Root<Review> root, CriteriaBuilder builder) {
        return builder.equal(root.get("user").get("id"), id);
    }
}
