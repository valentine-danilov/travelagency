package com.epam.travelagency.service;

import com.epam.travelagency.bean.User;
import com.epam.travelagency.repository.Repository;
import com.epam.travelagency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> readAll() {
        return repository.read();
    }

    public User readById(Integer id) {
        return repository.read(id);
    }

    public void update(Integer id, String login, String password) {
        User user = constructUser(id, login, password);
        repository.update(user);
    }

    public void deleteById(Integer id) {
        repository.delete(id);
    }

    public void add(String login, String password) {
        User user = constructUser(null, login, password);
        repository.create(user);
    }

    private User constructUser(Integer id,
                               String login,
                               String password) {
        User user = new User();
        if (id != null) {
            user.setId(id);
        }
        user.setLogin(login);
        user.setPassword(password);
        return user;
    }
}
