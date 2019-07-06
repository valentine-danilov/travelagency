package com.epam.travelagency.repository;

import com.epam.travelagency.entity.AbstractEntity;
import com.epam.travelagency.repository.specification.ISpecification;

import javax.persistence.NoResultException;
import java.util.List;

public interface IRepository<T extends AbstractEntity, I> {
    void add(T e);

    T get(I id) throws NoResultException;

    void update(T e);

    void delete(I id);

    List<T> getAll();

    List<T> getAllWithOffsetAndMaxSize(Integer offset, Integer maxResult);

    <C extends ISpecification<T>> List<T>
    findAllBySpecification(List<C> specifications);

    <C extends ISpecification<T>> List<T>
    findAllBySpecificationWithOffsetAndMaxSize(List<C> specifications,
                                               Integer offset,
                                               Integer maxResult);

    <C extends ISpecification<T>> Long getPageNumber(List<C> specifications);
}
