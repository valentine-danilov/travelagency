package com.epam.travelagency.repository;

import com.epam.travelagency.bean.Review;

import java.util.List;

@org.springframework.stereotype.Repository
public class ReviewRepository extends Repository<Review> {
    @Override
    public Integer create(Review entity) {
        return storage.create(entity);
    }

    @Override
    public Review read(Integer id) {
        return storage.read(id);
    }

    @Override
    public void update(Review entity) {
        storage.update(entity);
    }

    @Override
    public void delete(Integer id) {
        storage.delete(id);
    }

    @Override
    public List<Review> read() {
        return storage.read();
    }
}
