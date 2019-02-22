package com.epam.travelagency.repository;

import com.epam.travelagency.bean.Country;

import java.util.List;

public class CountryRepository extends Repository<Country> {
    @Override
    public Integer create(Country entity) {
        return storage.create(entity);
    }

    @Override
    public Country read(Integer id) {
        return storage.read(id);
    }

    @Override
    public void update(Country entity) {
        storage.update(entity);
    }

    @Override
    public void delete(Integer id) {
        storage.delete(id);
    }

    @Override
    public List<Country> read() {
        return storage.read();
    }
}
