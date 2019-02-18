package com.epam.travelagency.dataaccess.postgre;

import com.epam.travelagency.bean.User;
import com.epam.travelagency.dataaccess.UserDao;
import com.epam.travelagency.dataaccess.postgre.mappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(User entity) {
        jdbcTemplate.update("INSERT INTO user (id, login, password) SET id=?, login=?, password=?",
                entity.getId(),
                entity.getLogin(),
                entity.getPassword());
    }

    public User read(Integer id) {
        return jdbcTemplate.queryForObject("SELECT login, password from user where user.id=?",
                new UserRowMapper(), id);
    }

    public void update(User entity) {
        jdbcTemplate.update("UPDATE user SET login=?, password=? WHERE user.id=?", entity.getId());
    }

    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM user WHERE user.id=?", id);
    }
}
