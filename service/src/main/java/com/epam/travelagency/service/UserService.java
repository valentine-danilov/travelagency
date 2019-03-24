package com.epam.travelagency.service;

import com.epam.travelagency.entity.User;
import com.epam.travelagency.enumeration.Role;
import com.epam.travelagency.repository.IUserRepository;
import com.epam.travelagency.service.exception.LoginNotUniqueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final
    IUserRepository repository;

    @Autowired
    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    public List<User> readAll() {
        return repository.getAll();
    }

    public User readById(Integer id) {
        return repository.get(id);
    }

    public void update(Integer id, String login, String password) {
        User user = constructUser(id, login, password);
        repository.update(user);
    }

    public void deleteById(Integer id) {
        repository.delete(id);
    }

    public void add(String login, String password) throws LoginNotUniqueException {
        User user = constructUser(null, login, password);
        if (unique(login)) {
            repository.add(user);
        } else throw new LoginNotUniqueException(login);
    }

    public Optional<User> findOneByLogin(String login) {
        return repository.findOneByLogin(login);
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
        user.setRole(Role.USER);
        return user;
    }

    private boolean unique(String login) {
        return repository.findOneByLogin(login).isEmpty();
    }
}
