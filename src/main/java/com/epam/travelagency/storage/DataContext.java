package com.epam.travelagency.storage;

import com.epam.travelagency.bean.AbstractEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DataContext<T extends AbstractEntity> {
    void create(T entity);

    T read(Integer id);

    void update(T entity);

    void delete(Integer id);

    List<T> read();


}
