package com.epam.travelagency.storage.posgresql;

import com.epam.travelagency.bean.Hotel;
import com.epam.travelagency.storage.DataContext;
import com.epam.travelagency.storage.exception.NullGeneratedKeyException;
import com.epam.travelagency.storage.mapper.HotelRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.List;

public class HotelDataContext implements DataContext<Hotel> {

    private static Logger LOG = LoggerFactory.getLogger(HotelDataContext.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public HotelDataContext(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer create(Hotel entity) {
        KeyHolder generatedIdHolder = new GeneratedKeyHolder();
        final String sql = "INSERT INTO \"hotel\" (name, stars, website, latitude, longitude, feature)" +
                " VALUES (?,?,?,?,?,?::hotel_feature)";
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setByte(2, entity.getStars());
            preparedStatement.setString(3, entity.getWebsite());
            preparedStatement.setBigDecimal(4, entity.getLatitude());
            preparedStatement.setBigDecimal(5, entity.getLongitude());
            preparedStatement.setString(6, entity.getFeatures().name().toLowerCase());
            return preparedStatement;
        }, generatedIdHolder);
        LOG.info(String.format("Hotel '%s' was created in database", entity.getName()));
        try {
            return generatedIdHolder.getKey().intValue();
        } catch (NullPointerException e) {
            throw new NullGeneratedKeyException(e);
        }
    }

    @Override
    public Hotel read(Integer id) {
        return jdbcTemplate.queryForObject("SELECT id, name, stars, website," +
                        " latitude, longitude, feature FROM hotel WHERE id=?",
                new HotelRowMapper(), id);

    }

    @Override
    public void update(Hotel entity) {
        jdbcTemplate.update("UPDATE \"hotel\" SET name=?, stars=?, website=?," +
                        "latitude=?, longitude=?, feature=?::hotel_feature WHERE id=?",
                entity.getName(),
                entity.getStars(),
                entity.getWebsite(),
                entity.getLatitude(),
                entity.getLatitude(),
                entity.getFeatures().name().toLowerCase());
        LOG.info(String.format("Hotel '%s' was updated in database", entity.getName()));

    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM hotel where id=?", id);
        LOG.info(String.format("Hotel with id '%d' was deleted from database", id));

    }

    @Override
    public List<Hotel> read() {
        return jdbcTemplate.query("SELECT id, name, stars, website," +
                        " latitude, longitude, feature FROM hotel",
                new HotelRowMapper());
    }

}
