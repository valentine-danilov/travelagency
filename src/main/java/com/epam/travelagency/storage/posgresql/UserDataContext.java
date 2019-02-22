package com.epam.travelagency.storage.posgresql;

import com.epam.travelagency.bean.User;
import com.epam.travelagency.storage.DataContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

public class UserDataContext implements DataContext<User> {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDataContext(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer create(User entity) {
        KeyHolder generatedIdHolder = new GeneratedKeyHolder();
        final String sql = "INSERT INTO \"user\" (login, password) VALUES (?,?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            return preparedStatement;
        }, generatedIdHolder);

        return Objects.requireNonNull(generatedIdHolder.getKey()).intValue();
    }

    public User read(Integer id) {
        return jdbcTemplate.queryForObject("SELECT id, login, password FROM \"user\" WHERE \"user\".id=?",
                new BeanPropertyRowMapper<>(User.class), id);
    }

    public void update(User entity) {
        jdbcTemplate.update("UPDATE \"user\" SET login=?, password=? WHERE \"user\".id=?",
                entity.getLogin(),
                entity.getPassword(),
                entity.getId());
    }

    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM \"user\" WHERE \"user\".id=?", id);
    }

    public List<User> read() {
        return jdbcTemplate.query("SELECT id, login, password FROM \"user\"", new BeanPropertyRowMapper<>(User.class));
    }
}
