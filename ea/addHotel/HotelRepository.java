package com.epam.travelagency.repository.impl.postgre;

import com.epam.travelagency.entity.Hotel;
import com.epam.travelagency.repository.IHotelRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository("hotelRepository")
public class HotelRepository extends BaseRepository<Hotel> implements IHotelRepository {

    public HotelRepository() {
        super.setClazz(Hotel.class);
    }

    @Override
    public List<Hotel> getAll() {
        TypedQuery<Hotel> query = entityManager.createNamedQuery("Hotel.findAll", Hotel.class);
        return query.getResultList();
    }
}
