package com.epam.travelagency.service;

import com.epam.travelagency.bean.Country;

import java.util.List;

public class CountryService extends AbstractService<Country> {
    @Override
    public List<Country> readAll() {
        return repository.read();
    }

    @Override
    public Country readById(Integer id) {
        return repository.read(id);
    }

    @Override
    public void deleteById(Integer id) {
        repository.delete(id);
    }

    @Override
    public void add(Country entity) {
        repository.create(entity);
    }

    @Override
    public void update(Country entity) {
        repository.update(entity);
    }
}
