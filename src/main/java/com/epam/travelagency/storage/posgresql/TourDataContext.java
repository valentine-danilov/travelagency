package com.epam.travelagency.storage.posgresql;

import com.epam.travelagency.bean.Tour;
import com.epam.travelagency.storage.DataContext;
import com.epam.travelagency.storage.mapper.TourRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Types;
import java.util.List;

public class TourDataContext implements DataContext<Tour> {

    private static final String SELECT_FROM_TOUR =
            "SELECT t.id as tour_id, photo, date, duration, description," +
                    " cost, tour_type, hotel_id, country_id," +
                    " c.name as country_name," +
                    " h.name as hotel_name, h.stars, h.website," +
                    " h.latitude, h.longitude, h.feature " +
                    " FROM tour t" +
                    " JOIN country c on t.country_id = c.id" +
                    " JOIN hotel h on t.hotel_id = h.id";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TourDataContext(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Tour entity) {
        jdbcTemplate.update(
                "INSERT INTO tour (photo, date, duration," +
                        " description, cost, tour_type, hotel_id, country_id)" +
                        " VALUES (?,?::DATE,?,?,?,?::tour_type,?,?)",
                preparedStatement -> {
                    preparedStatement.setString(1, entity.getPhoto());
                    preparedStatement.setString(2, entity.getDate());
                    preparedStatement.setInt(3, entity.getDuration());
                    preparedStatement.setString(4, entity.getDescription());
                    preparedStatement.setBigDecimal(5, entity.getCost());
                    preparedStatement.setString(6, entity.getTourType().name().toLowerCase());
                    preparedStatement.setInt(7, entity.getHotel().getId());
                    preparedStatement.setInt(8, entity.getCountry().getId());
                });
    }

    @Override
    public Tour read(Integer id) {
        return jdbcTemplate.queryForObject(
                SELECT_FROM_TOUR + " WHERE t.id=?",
                new TourRowMapper(), id);
    }

    @Override
    public void update(Tour entity) {
        jdbcTemplate.update("UPDATE tour" +
                        " SET photo=?, date=?, duration=?," +
                        " description=?, cost=?, tour_type=?" +
                        " WHERE id=?",
                preparedStatement -> {
                    preparedStatement.setString(1, entity.getPhoto());
                    preparedStatement.setObject(2, entity.getDate(), Types.DATE);
                    preparedStatement.setInt(3, entity.getDuration());
                    preparedStatement.setString(4, entity.getDescription());
                    preparedStatement.setBigDecimal(5, entity.getCost());
                    preparedStatement.setString(6, entity.getTourType().name().toLowerCase());
                }
        );
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM tour WHERE id=?", id);
    }

    @Override
    public List<Tour> read() {
        return jdbcTemplate.query(SELECT_FROM_TOUR, new TourRowMapper());
    }
}
