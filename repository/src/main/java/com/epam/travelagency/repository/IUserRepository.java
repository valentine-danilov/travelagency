package com.epam.travelagency.repository;

import com.epam.travelagency.entity.User;

import javax.persistence.NoResultException;
import java.util.Optional;

public interface IUserRepository extends IRepository<User, Integer> {
    Optional<User> findOneByLogin(String login);
}
