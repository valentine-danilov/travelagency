package com.epam.travelagency.repository;

import com.epam.travelagency.bean.User;
import com.epam.travelagency.storage.DataContext;
import com.epam.travelagency.storage.UserDataContext;

public class UserRepository implements Repository<User> {

    private UserDataContext storage;

    public void setStorage(UserDataContext storage) {
        this.storage = storage;
    }

    public void create(User entity) {
        storage.create(entity);
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
}
