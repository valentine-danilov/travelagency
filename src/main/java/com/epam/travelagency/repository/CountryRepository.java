package com.epam.travelagency.repository;

import com.epam.travelagency.bean.Country;

public class CountryRepository extends Repository<Country> {
    @Override
    public void create(Country entity) {
        storage.create(entity);
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
}
