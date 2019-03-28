package com.epam.travelagency.repository.impl.postgre;

import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.entity.User;
import com.epam.travelagency.repository.ITourRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    @Override
    public List<Tour> findAllByUser(Integer id) {
        TypedQuery<Tour> queue = entityManager.createNamedQuery("Tour.findAllByUser", Tour.class);
        queue.setParameter(1, id);
        return queue.getResultList();
    }

    @Override
    public Tour get(Integer id) throws NoResultException {
        return entityManager.find(Tour.class, id);
    }
}
