package com.epam.travelagency.storage.posgresql;

import com.epam.travelagency.bean.User;
import com.epam.travelagency.storage.DataContext;
import com.epam.travelagency.storage.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDataContext implements DataContext<User> {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDataContext(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(User entity) {
        jdbcTemplate.update("INSERT INTO \"user\" (id, login, password) VALUES (?,?,?)",
                entity.getId(),
                entity.getLogin(),
                entity.getPassword());
    }

    public User read(Integer id) {
        return jdbcTemplate.queryForObject("SELECT id, login, password FROM \"user\" WHERE \"user\".id=?",
                new UserRowMapper(), id);
    }

    public void update(User entity) {
        jdbcTemplate.update("UPDATE \"user\" SET login=?, password=? WHERE \"user\".id=?",
                entity.getLogin(),
                entity.getPassword());
    }

    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM \"user\" WHERE \"user\".id=?", id);
    }
}
