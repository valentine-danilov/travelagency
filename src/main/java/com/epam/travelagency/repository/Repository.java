package com.epam.travelagency.repository;

import com.epam.travelagency.bean.AbstractEntity;

public interface Repository<T extends AbstractEntity> {
    void create(T entity);

    T read(Integer id);

    void update(T entity);

    void delete(Integer id);
}
