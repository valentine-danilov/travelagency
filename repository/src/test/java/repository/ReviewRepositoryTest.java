package repository;

import com.epam.travelagency.entity.Review;
import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.entity.User;
import com.epam.travelagency.repository.IReviewRepository;
import com.epam.travelagency.repository.specification.impl.postgre.review.ByTour;
import com.epam.travelagency.repository.specification.impl.postgre.review.ByUserId;
import config.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ReviewRepositoryTest {

    private static Logger LOG = LoggerFactory.getLogger(ReviewRepositoryTest.class);

    @Autowired
    private IReviewRepository reviewRepository;

    private static final Integer INITIAL_ENTRY_NUMBER = 4;
    private static final Integer ID = 1;
    private static final Integer ID_2 = 2;
    private static final Integer QUERY_SIZE = 2;

    @Autowired
    @Qualifier("testReview1")
    private Review expected;

    @Test
    public void shouldBeReadByIdCorrectly() {
        Review actual = reviewRepository.get(ID);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldReadAll() {
        List<Review> reviewList = reviewRepository.getAll();
        Integer actual = reviewList.size();
        Assert.assertEquals(INITIAL_ENTRY_NUMBER, actual);
    }

    @Test
    @DirtiesContext
    public void shouldDeleteById() {
        reviewRepository.delete(ID);
        Review actual = reviewRepository.get(ID);
        Assert.assertNull(actual);
    }

    @Test
    @DirtiesContext
    public void shouldBeUpdated() {
        expected.setId(2);
        reviewRepository.update(expected);
        Review actual = reviewRepository.get(2);
        Assert.assertEquals(expected, actual);
    }

    @Test
    @DirtiesContext
    public void shouldBeAdded() {
        expected.setId(null);
        reviewRepository.add(expected);
        Review actual = reviewRepository.get(5);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldReadBySpecificTour() {
        List<Review> actualResult = reviewRepository
                .findAllBySpecification(
                        List.of(new ByTour(
                                new Tour() {{
                                    setId(ID_2);
                                }})
                        )
                );
        List<Review> expected = List.of(
                new Review() {{
                    setId(1);
                }}, new Review() {{
                    setId(3);
                }}
        );
        Integer actualSize = actualResult.size();
        Assert.assertFalse(actualResult.isEmpty());
        Assert.assertEquals(actualSize, QUERY_SIZE);
        Assert.assertEquals(expected, actualResult);
    }

    @Test
    public void shouldReadBySpecificUser() {
        List<Review> actual = reviewRepository
                .findAllBySpecification(
                        List.of(new ByUserId(1)));
        List<Review> expected = List.of(
                new Review() {{
                    setId(1);
                }}, new Review() {{
                    setId(3);
                }}
        );
        int actualSize = actual.size();
        Assert.assertFalse(actual.isEmpty());
        Assert.assertEquals(2, actualSize);
        Assert.assertEquals(expected, actual);
    }
}
