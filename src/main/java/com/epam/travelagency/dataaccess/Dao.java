package com.epam.travelagency.dataaccess;

import com.epam.travelagency.bean.AbstractEntity;

public interface Dao<T extends AbstractEntity> {
    void create(T entity);

    T read(Integer id);

    void update(T entity);

    void delete(Integer id);

}
