package com.epam.travelagency.storage.posgresql;

import com.epam.travelagency.bean.Country;
import com.epam.travelagency.storage.DataContext;
import com.epam.travelagency.storage.exception.NullGeneratedKeyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.List;

public class CountryDataContext implements DataContext<Country> {

    private static Logger LOG = LoggerFactory.getLogger(CountryDataContext.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CountryDataContext(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer create(Country entity) {
        KeyHolder generatedIdHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO country (name) VALUES (?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setString(1, entity.getName());
            return preparedStatement;
        }, generatedIdHolder);
        LOG.info(String.format("Country %s was created in database", entity.getName()));
        try {
            return generatedIdHolder.getKey().intValue();
        } catch (NullPointerException e) {
            throw new NullGeneratedKeyException(e);
        }
    }

    @Override
    public Country read(Integer id) {
        return jdbcTemplate.queryForObject("SELECT id, name FROM country WHERE id=?", new BeanPropertyRowMapper<>(Country.class), id);
    }

    @Override
    public void update(Country entity) {
        jdbcTemplate.update("UPDATE country SET name=? WHERE id=?", entity.getName(), entity.getId());
        LOG.info(String.format("Country %s was updated in database", entity.getName()));
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM country WHERE id=?", id);
        LOG.info(String.format("Country with id '%d' was deleted from database", id));

    }

    @Override
    public List<Country> read() {
        return jdbcTemplate.query("SELECT id, name FROM country", new BeanPropertyRowMapper<>(Country.class));
    }
}
