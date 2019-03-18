package com.epam.travelagency.repository.impl.postgre;

import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.entity.User;
import com.epam.travelagency.repository.ITourRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository("tourRepository")
public class TourRepository extends BaseRepository<Tour> implements ITourRepository {

    public TourRepository() {
        super.setClazz(Tour.class);
    }

    @Override
    public List<Tour> getAll() {
        TypedQuery<Tour> query = entityManager.createNamedQuery("Tour.findAll", Tour.class);
        return query.getResultList();
    }

    public List<Tour> findAllByUser(User user) {
        TypedQuery<Tour> queue = entityManager.createNamedQuery("Tour.findAllByUser", Tour.class);
        queue.setParameter(1, user.getId());
        return queue.getResultList();
    }
}
