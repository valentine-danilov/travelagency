package com.epam.travelagency.repository.impl.postgre;

import com.epam.travelagency.entity.Country;
import com.epam.travelagency.repository.ICountryRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository("countryRepository")
public class CountryRepository extends BaseRepository<Country> implements ICountryRepository {

    public CountryRepository() {
        super.setClazz(Country.class);
    }

    @Override
    public List<Country> getAll() {
        TypedQuery<Country> query = entityManager.createNamedQuery("Country.findAll", Country.class);
        return query.getResultList();
    }
}
