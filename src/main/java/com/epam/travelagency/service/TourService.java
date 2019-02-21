package com.epam.travelagency.service;

import com.epam.travelagency.bean.Tour;
import com.epam.travelagency.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourService extends AbstractService<Tour> {

    @Autowired
    public TourService(TourRepository repository) {
        super.repository = repository;
    }

    @Override
    public List<Tour> readAll() {
        return repository.read();
    }

    @Override
    public Tour readById(Integer id) {
        return repository.read(id);
    }

    @Override
    public void deleteById(Integer id) {
        repository.delete(id);
    }

    @Override
    public void add(Tour tour) {
        repository.create(tour);
    }

    @Override
    public void update(Tour tour) {
        repository.update(tour);
    }
}
