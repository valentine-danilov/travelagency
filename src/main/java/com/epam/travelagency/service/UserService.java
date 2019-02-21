package com.epam.travelagency.service;

import com.epam.travelagency.bean.User;
import com.epam.travelagency.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends AbstractService<User> {

    @Autowired
    public UserService(Repository<User> repository) {
        super.repository = repository;
    }

    @Override
    public List<User> readAll() {
        return repository.read();
    }

    @Override
    public User readById(Integer id) {
        return repository.read(id);
    }

    @Override
    public void update(User user) {
        repository.update(user);
    }

    @Override
    public void deleteById(Integer id) {
        repository.delete(id);
    }

    @Override
    public void add(User user) {
        repository.create(user);
    }
}
