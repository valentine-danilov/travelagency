package com.epam.travelagency.service;

import com.epam.travelagency.entity.Country;
import com.epam.travelagency.repository.ICountryRepository;
import com.epam.travelagency.repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryService {

    private final
    ICountryRepository repository;

    @Autowired
    public CountryService(ICountryRepository repository) {
        this.repository = repository;
    }

    public List<Country> readAll() {
        return repository.getAll();
    }

    public Country readById(Integer id) {
        return repository.get(id);
    }

    public void deleteById(Integer id) {
        repository.delete(id);
    }

    public void update(Integer countryId, String countryName) {
        Country country = new Country() {
            {
                setId(countryId);
                setName(countryName);
            }
        };
        repository.update(country);
    }

    @Transactional
    public void add(String countryName) {
        Country country = new Country() {
            {
                setName(countryName);
            }
        };
        repository.add(country);
    }
}
