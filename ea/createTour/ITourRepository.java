package com.epam.travelagency.repository;

import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.entity.User;

import java.util.List;

public interface ITourRepository extends IRepository<Tour, Integer> {
    List<Tour> findAllByUser(Integer id);
}