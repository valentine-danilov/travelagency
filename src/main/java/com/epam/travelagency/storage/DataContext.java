package com.epam.travelagency.storage;

import com.epam.travelagency.bean.AbstractEntity;

public interface DataContext<T extends AbstractEntity> {
    void create(T entity);

    T read(Integer id);

    void update(T entity);

    void delete(Integer id);
}
