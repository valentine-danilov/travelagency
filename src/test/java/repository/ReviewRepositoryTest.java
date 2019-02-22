package repository;

import com.epam.travelagency.bean.Review;
import com.epam.travelagency.repository.ReviewRepository;
import com.epam.travelagency.storage.posgresql.ReviewDataContext;
import com.opentable.db.postgres.embedded.FlywayPreparer;
import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.PreparedDbRule;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class ReviewRepositoryTest {
    private static final Integer INITIAL_NUMBER_OF_ENTRIES = 4;
    @ClassRule
    public static PreparedDbRule db =
            EmbeddedPostgresRules.preparedDatabase(
                    FlywayPreparer.forClasspathLocation("db/migration"));

    private static ReviewRepository repository;

    private static Review testReview;

    @BeforeClass
    public static void init(){
        JdbcTemplate template = new JdbcTemplate(db.getTestDatabase());
        ReviewDataContext dataContext = new ReviewDataContext(template);
        repository = new ReviewRepository();
        repository.setDataContext(dataContext);

        testReview = new Review();
        testReview.setId(5);
        testReview.setDate("2012-12-12");
        testReview.setUserId(1);
        testReview.setTourId(1);
    }

    @Test
    public void shouldReadAllEntries(){
        Integer actual = repository.read().size();
        Assert.assertEquals(INITIAL_NUMBER_OF_ENTRIES, actual);
    }

    @Test
    public void shouldReadCorrectly(){
        Review review = repository.read(2);
        testReview.setDate("2012-12-11");
        testReview.setText("Some words2");
        testReview.setUserId(1);
        testReview.setTourId(1);
        Assert.assertEquals(testReview.getDate(), review.getDate());
        Assert.assertEquals(testReview.getText(), review.getText());
        Assert.assertEquals(testReview.getUserId(), review.getUserId());
        Assert.assertEquals(testReview.getTourId(), review.getTourId());
    }

    @Test
    public void shouldCreate(){
        Integer generatedId = repository.create(testReview);
        Assert.assertEquals(testReview, repository.read(generatedId));
    }

    @Test
    public void shouldUpdate(){
        testReview.setId(4);
        testReview.setText("Updated text");
        testReview.setDate("2012-12-12");
        testReview.setTourId(2);
        testReview.setUserId(2);
        repository.update(testReview);
        Assert.assertEquals(testReview, repository.read(4));
    }

    @Test(expected = Exception.class)
    public void shouldDelete(){
        repository.delete(1);
        repository.read(1);

    }

}
