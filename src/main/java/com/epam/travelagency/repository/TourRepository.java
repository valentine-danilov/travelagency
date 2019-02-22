package com.epam.travelagency.repository;

import com.epam.travelagency.bean.Tour;

import java.util.List;

@org.springframework.stereotype.Repository
public class TourRepository extends Repository<Tour> {
    @Override
    public Integer create(Tour entity) {
        return storage.create(entity);
    }

    @Override
    public Tour read(Integer id) {
        return storage.read(id);
    }

    @Override
    public void update(Tour entity) {
        storage.update(entity);
    }

    @Override
    public void delete(Integer id) {
        storage.delete(id);
    }

    public List<Tour> read(){
        return storage.read();
    }
}
