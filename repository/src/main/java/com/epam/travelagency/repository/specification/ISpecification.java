package com.epam.travelagency.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface ISpecification<T> {
    Predicate toPredicate(Root<T> root, CriteriaBuilder builder);
}
