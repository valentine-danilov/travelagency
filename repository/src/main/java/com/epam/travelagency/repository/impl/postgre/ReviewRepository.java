package com.epam.travelagency.repository.impl.postgre;

import com.epam.travelagency.entity.Review;
import com.epam.travelagency.repository.IReviewRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository("reviewRepository")
public class ReviewRepository extends BaseRepository<Review> implements IReviewRepository {

    public ReviewRepository() {
        super.setClazz(Review.class);
    }

    @Override
    public List<Review> getAll() {
        TypedQuery<Review> query = entityManager.createNamedQuery("Review.findAll", Review.class);
        return query.getResultList();
    }
}
