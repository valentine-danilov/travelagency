package com.epam.travelagency.service;

import com.epam.travelagency.bean.AbstractEntity;
import com.epam.travelagency.repository.Repository;

import java.util.List;

public abstract class AbstractService<T extends AbstractEntity> {
    protected Repository<T> repository;

    public abstract List<T> readAll();

    public abstract T readById(Integer id);

    public abstract void deleteById(Integer id);

    public abstract void add(T entity);

    public abstract void update(T entity);
}
