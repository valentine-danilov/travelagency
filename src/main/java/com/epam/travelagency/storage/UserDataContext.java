package com.epam.travelagency.storage;

import com.epam.travelagency.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDataContext implements DataContext<User> {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDataContext(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(User entity) {

    }

    public User read(Integer id) {
        return null;
    }

    public void update(User entity) {

    }

    public void delete(Integer id) {

    }
}
