package com.epam.travelagency.storage.mapper;

import com.epam.travelagency.bean.Review;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewRowMapper implements RowMapper<Review> {
    @Override
    public Review mapRow(ResultSet resultSet, int i) throws SQLException {
        Review review = new Review();
        review.setId(resultSet.getInt("id"));
        review.setDate(resultSet.getString("date"));
        review.setText(resultSet.getString("text"));
        review.setTourId(resultSet.getInt("tour_id"));
        review.setUserId(resultSet.getInt("user_id"));
        return review;
    }
}
