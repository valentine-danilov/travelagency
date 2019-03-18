package com.epam.travelagency.repository;

import com.epam.travelagency.entity.AbstractEntity;
import com.epam.travelagency.repository.specification.ISpecification;

import java.util.List;

public interface IRepository<T extends AbstractEntity, I> {
    void add(T e);
    T get(I id);
    void update(T e);
    void delete(I id);
    List<T> getAll();
    List<T> findAllBySpecification(ISpecification<T> specification);
}
