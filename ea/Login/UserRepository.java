package com.epam.travelagency.repository.impl.postgre;

import com.epam.travelagency.entity.User;
import com.epam.travelagency.repository.IUserRepository;
import com.epam.travelagency.repository.specification.ISpecification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository("userRepository")
public class UserRepository extends BaseRepository<User> implements IUserRepository {

    public UserRepository() {
        super.setClazz(User.class);
    }

    public List<User> getAll() {
        TypedQuery<User> query = entityManager.createNamedQuery("User.findAll", User.class);
        return query.getResultList();
    }

    public List<User> findAllBySpecification(ISpecification<User> specification) {
        return null;
    }

    @Override
    public Optional<User> findOneByLogin(String login) {
        try {
            TypedQuery<User> query = entityManager.createNamedQuery("User.findOneByLogin", User.class);
            query.setParameter("login", login);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }

    }

    @Override
    @Transactional
    public void buyTour(Integer userId, Integer tourId) {
        Query query = entityManager.createNamedQuery("User.buyTour");
        query.setParameter(1, userId);
        query.setParameter(2, tourId);
        query.executeUpdate();
    }
}
