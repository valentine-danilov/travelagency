package com.epam.travelagency.storage.posgresql;

import com.epam.travelagency.bean.Review;
import com.epam.travelagency.storage.DataContext;
import com.epam.travelagency.storage.mapper.ReviewRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Repository
public class ReviewDataContext implements DataContext<Review> {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ReviewDataContext(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer create(Review entity) {
        KeyHolder generatedIdHolder = new GeneratedKeyHolder();
        final String sql = "INSERT INTO review (date, text, user_id, tour_id) VALUES (?::DATE,?,?,?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getDate());
            preparedStatement.setString(2, entity.getText());
            preparedStatement.setInt(3, entity.getUserId());
            preparedStatement.setInt(4, entity.getTourId());
            return preparedStatement;
        }, generatedIdHolder);
        return Objects.requireNonNull(generatedIdHolder.getKey()).intValue();
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
