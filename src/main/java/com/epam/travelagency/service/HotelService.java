package com.epam.travelagency.service;

import com.epam.travelagency.bean.Hotel;
import com.epam.travelagency.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService extends AbstractService<Hotel> {

    @Autowired
    public HotelService(HotelRepository repository) {
        super.repository = repository;
    }

    @Override
    public List<Hotel> readAll() {
        return repository.read();
    }

    @Override
    public Hotel readById(Integer id) {
        return repository.read(id);
    }

    @Override
    public void update(Hotel user) {
        repository.update(user);
    }

    @Override
    public void deleteById(Integer id) {
        repository.delete(id);
    }

    @Override
    public void add(Hotel user) {
        repository.create(user);
    }
}
