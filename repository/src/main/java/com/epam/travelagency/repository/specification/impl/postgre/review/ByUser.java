package com.epam.travelagency.repository.specification.impl.postgre.review;

import com.epam.travelagency.entity.Review;
import com.epam.travelagency.entity.User;
import com.epam.travelagency.repository.specification.ReviewSpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ByUser implements ReviewSpecification {
    private User user;

    public ByUser(User user) {
        this.user = user;
    }

    @Override
    public Predicate toPredicate(Root<Review> root, CriteriaBuilder builder) {
        return builder.equal(root.get("user"), user);
    }
}
