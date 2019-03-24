package repository;

import com.epam.travelagency.entity.Review;
import com.epam.travelagency.entity.User;
import com.epam.travelagency.repository.IRepository;
import com.epam.travelagency.repository.IUserRepository;
import config.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class UserRepositoryTest {
    private static final Integer INITIAL_ENTRY_NUMBER = 4;
    private static final Integer ID_TO_READ = 1;

    @Autowired
    private IUserRepository userRepository;

    @Qualifier("testUser")
    @Autowired
    private User expected;

    @Autowired
    private Review review;

    @Test
    public void shouldReadByIdCorrectly() {
        expected.setId(1);
        expected.setLogin("user1");
        expected.setReviewList(List.of(review));
        User actual = userRepository.get(ID_TO_READ);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldReadAllData() {
        List<User> actualUsers = userRepository.getAll();
        Integer actual = actualUsers.size();
        Assert.assertEquals(INITIAL_ENTRY_NUMBER, actual);
    }

    @Test
    @DirtiesContext
    public void deleteShouldBeInvoked() {
        userRepository.delete(4);
        User actual = userRepository.get(4);
        Assert.assertNull(actual);
    }

    /*@Test
    @DirtiesContext
    public void shouldBeAdded() {
        expected.setId(null);
        expected.setLogin("user5");
        expected.setReviewList(null);
        userRepository.add(expected);
        User actual = userRepository.get(5);
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getLogin(), actual.getLogin());
        Assert.assertEquals(expected.getPassword(), actual.getPassword());
    }*/

    /*@Test
    @DirtiesContext
    public void updateShouldBeInvoked() {
        expected.setId(1);
        userRepository.update(expected);
        User actual = userRepository.get(1);
        Assert.assertEquals(expected, actual);
    }*/
}
