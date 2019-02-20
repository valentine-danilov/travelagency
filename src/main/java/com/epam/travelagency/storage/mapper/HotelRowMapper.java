package com.epam.travelagency.storage.mapper;

import com.epam.travelagency.bean.Hotel;
import com.epam.travelagency.bean.enumeration.HotelFeature;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class HotelRowMapper implements RowMapper<Hotel> {
    @Override
    public Hotel mapRow(ResultSet resultSet, int i) throws SQLException {
        Hotel hotel = new Hotel();
        hotel.setId(resultSet.getInt("id"));
        hotel.setName(resultSet.getString("name"));
        hotel.setStars(resultSet.getByte("stars"));
        hotel.setWebsite(resultSet.getString("website"));
        hotel.setLatitude(resultSet.getBigDecimal("latitude"));
        hotel.setLongitude(resultSet.getBigDecimal("longitude"));
        hotel.setFeatures(HotelFeature.valueOf(resultSet.getString("feature").toUpperCase()));
        return hotel;
    }
}
