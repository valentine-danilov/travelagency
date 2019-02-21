package com.epam.travelagency.storage.posgresql;

import com.epam.travelagency.bean.Review;
import com.epam.travelagency.storage.DataContext;
import com.epam.travelagency.storage.mapper.ReviewRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewDataContext implements DataContext<Review> {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ReviewDataContext(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Review entity) {
        jdbcTemplate.update("INSERT INTO review (date, text, user_id, tour_id) VALUES (?::DATE,?,?,?)",
                preparedStatement -> {
                    preparedStatement.setString(1, entity.getDate());
                    preparedStatement.setString(2, entity.getText());
                    preparedStatement.setInt(3, entity.getUserId());
                    preparedStatement.setInt(4, entity.getTourId());
                });
    }

    @Override
    public Review read(Integer id) {
        return jdbcTemplate.queryForObject("SELECT id, date, text, user_id, tour_id" +
                " FROM review" +
                " WHERE id=?", new ReviewRowMapper(), id);
    }

    @Override
    public void update(Review entity) {
        jdbcTemplate.update("UPDATE review" +
                        " SET date=?::DATE, text=?, user_id=?, tour_id=?" +
                        " WHERE id=?",
                preparedStatement -> {
                    preparedStatement.setString(1, entity.getDate());
                    preparedStatement.setString(2, entity.getText());
                    preparedStatement.setInt(3, entity.getUserId());
                    preparedStatement.setInt(4, entity.getTourId());
                    preparedStatement.setInt(5, entity.getId());
                });
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM review where id=?", id);
    }

    @Override
    public List<Review> read() {
        return jdbcTemplate.query("SELECT id, date, text, user_id, tour_id" +
                " FROM review", new ReviewRowMapper());
    }
}
