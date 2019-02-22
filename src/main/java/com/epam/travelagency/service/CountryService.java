package com.epam.travelagency.service;

import com.epam.travelagency.bean.Country;
import com.epam.travelagency.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CountryService {

    private final CountryRepository repository;

    @Autowired
    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }

    public List<Country> readAll() {
        return repository.read();
    }

    public Country readById(Integer id) {
        return repository.read(id);
    }

    public void deleteById(Integer id) {
        repository.delete(id);
    }

    public void add(String countryName) {
        Country country = new Country();
        country.setName(countryName);
        repository.create(country);
    }

    public void update(Integer id, String countryName) {
        Country country = new Country();
        country.setId(id);
        country.setName(countryName);
        repository.update(country);
    }
}
