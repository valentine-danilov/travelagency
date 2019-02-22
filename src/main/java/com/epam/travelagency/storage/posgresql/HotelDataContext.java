package com.epam.travelagency.storage.posgresql;

import com.epam.travelagency.bean.Hotel;
import com.epam.travelagency.storage.DataContext;
import com.epam.travelagency.storage.mapper.HotelRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

public class HotelDataContext implements DataContext<Hotel> {

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
        return Objects.requireNonNull(generatedIdHolder.getKey()).intValue();
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
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM hotel where id=?", id);
    }

    @Override
    public List<Hotel> read() {
        return jdbcTemplate.query("SELECT id, name, stars, website," +
                        " latitude, longitude, feature FROM hotel",
                new HotelRowMapper());
    }

}
