package com.epam.travelagency.repository.impl.postgre;

import com.epam.travelagency.entity.AbstractEntity;
import com.epam.travelagency.repository.IRepository;
import com.epam.travelagency.repository.specification.ISpecification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public T get(Integer id) {
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
    public List<T> findAllBySpecification(ISpecification<T> specification) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);
        Root<T> root = query.from(clazz);
        Predicate predicate = specification.toPredicate(root, criteriaBuilder);
        query.where(predicate);
        return entityManager.createQuery(query).getResultList();
    }
}
