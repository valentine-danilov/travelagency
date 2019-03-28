package com.epam.travelagency.repository.impl.postgre;

import com.epam.travelagency.entity.AbstractEntity;
import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.repository.IRepository;
import com.epam.travelagency.repository.specification.ISpecification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class BaseRepository<T extends AbstractEntity> implements IRepository<T, Integer> {

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<T> clazz;

    void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    @Transactional
    public void add(T e) {
        entityManager.persist(e);
    }

    @Override
    public T get(Integer id) throws NoResultException {
        return entityManager.find(clazz, id);
    }

    @Override
    @Transactional
    public void update(T e) {
        entityManager.merge(e);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        T e = get(id);
        entityManager.remove(e);
    }

    @Override
    public <C extends ISpecification<T>> List<T>
    findAllBySpecification(List<C> specifications) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);
        Root<T> root = query.from(clazz);
        for (ISpecification<T> specification: specifications) {
            Predicate predicate = specification.toPredicate(root, criteriaBuilder);
            query.where(predicate);
        }
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public <C extends ISpecification<T>> List<T>
    findAllBySpecificationWithOffsetAndMaxSize(List<C> specifications,
                                               Integer offset,
                                               Integer maxResult) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);
        Root<T> root = query.from(clazz);
        for (ISpecification<T> specification: specifications) {
            Predicate predicate = specification.toPredicate(root, criteriaBuilder);
            query.where(predicate);
        }
        TypedQuery<T> typedQuery = entityManager.createQuery(query);
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
                .count(countQuery.from(clazz)));
        return entityManager.createQuery(countQuery)
                .getSingleResult();
    }

    @Override
    public List<T> getAllWithOffsetAndMaxSize(Integer offset, Integer maxResult){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(clazz);
        Root<T> root = criteriaQuery.from(clazz);
        TypedQuery<T> typedQuery = entityManager
                .createQuery(criteriaQuery.select(root));
        typedQuery.setFirstResult(offset);
        typedQuery.setMaxResults(maxResult);
        return typedQuery.getResultList();
    }
}
