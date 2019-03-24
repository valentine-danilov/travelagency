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

    public List<Tour> getAllWithOffsetAndMaxSize(Integer offset, Integer maxResult){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Tour> criteriaQuery = builder.createQuery(Tour.class);
        Root<Tour> root = criteriaQuery.from(Tour.class);
        TypedQuery<Tour> typedQuery = entityManager
                .createQuery(criteriaQuery.select(root));
        typedQuery.setFirstResult(offset);
        typedQuery.setMaxResults(maxResult);
        return typedQuery.getResultList();
    }

    @Override
    public Long getPageNumber() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Long> countQuery = builder
                .createQuery(Long.class);
        countQuery.select(builder
                .count(countQuery.from(Tour.class)));
        return entityManager.createQuery(countQuery)
                .getSingleResult();
    }

    public List<Tour> findAllByUser(User user) {
        TypedQuery<Tour> queue = entityManager.createNamedQuery("Tour.findAllByUser", Tour.class);
        queue.setParameter(1, user.getId());
        return queue.getResultList();
    }

    @Override
    public Tour get(Integer id) throws NoResultException {
        Tour tour = entityManager.find(Tour.class, id);
        if(tour!=null){
            tour.getReviews().iterator();//костылииии

        }
        return tour;
    }
}
