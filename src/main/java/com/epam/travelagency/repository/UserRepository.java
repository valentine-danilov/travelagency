package com.epam.travelagency.repository;

import com.epam.travelagency.bean.User;

import java.util.List;

@org.springframework.stereotype.Repository
public class UserRepository extends Repository<User> {

    public Integer create(User entity) {
        return storage.create(entity);
    }

    public User read(Integer id) {
        return storage.read(id);
    }

    public void update(User entity) {
        storage.update(entity);
    }

    public void delete(Integer id) {
        storage.delete(id);
    }

    public List<User> read(){
        return storage.read();
    }
}
