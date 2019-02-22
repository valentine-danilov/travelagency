package com.epam.travelagency.storage.posgresql;

import com.epam.travelagency.bean.User;
import com.epam.travelagency.storage.DataContext;
import com.epam.travelagency.storage.exception.NullGeneratedKeyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.List;

public class UserDataContext implements DataContext<User> {

    private static Logger LOG = LoggerFactory.getLogger(UserDataContext.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDataContext(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer create(User entity) {
        KeyHolder generatedIdHolder = new GeneratedKeyHolder();
        final String sql = "INSERT INTO \"user\" (login, password) VALUES (?,?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            return preparedStatement;
        }, generatedIdHolder);
        LOG.info(String.format("User '%s' was created in database", entity.getLogin()));

        try {
            return generatedIdHolder.getKey().intValue();
        } catch (NullPointerException e) {
            throw new NullGeneratedKeyException(e);
        }
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
        LOG.info(String.format("User '%s' was updated in database", entity.getLogin()));
    }

    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM \"user\" WHERE \"user\".id=?", id);
        LOG.info(String.format("User with id '%d' was deleted from database", id));
    }

    public List<User> read() {
        return jdbcTemplate.query("SELECT id, login, password FROM \"user\"", new BeanPropertyRowMapper<>(User.class));
    }
}
