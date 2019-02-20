package repository;

import com.epam.travelagency.bean.User;
import com.epam.travelagency.repository.UserRepository;
import com.epam.travelagency.storage.posgresql.UserDataContext;
import com.opentable.db.postgres.embedded.FlywayPreparer;
import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.PreparedDbRule;
import config.TestConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class UserRepositoryTest {



    @Autowired
    private UserDataContext dataContext;
    @Autowired
    private UserRepository userRepository;

    private static final Integer EXPECTED_SIZE = 4;

    private User testUser = new User();

    @Before
    public void init() {

        userRepository.setDataContext(dataContext);
        testUser.setId(1);
        testUser.setLogin("testLogin");
        testUser.setPassword("testPswd");
    }

    @Test
    public void shouldNotBeEmpty() {
        User expectedUser = userRepository.read(1);
        Assert.assertNotNull(expectedUser.getId());
    }

    @Test
    public void shouldBeCreated() {
        testUser.setId(5);
        testUser.setLogin("test");
        userRepository.create(testUser);
        Assert.assertEquals(userRepository.read(testUser.getId()), testUser);
    }

    @Test
    public void shouldHasExpectedSize() {
        Integer actualSize = userRepository.read().size();
        Assert.assertEquals(EXPECTED_SIZE, actualSize);
    }

    @Test
    public void shouldBeUpdated() {
        testUser.setId(1);
        userRepository.update(testUser);
        Assert.assertEquals(userRepository.read(testUser.getId()), testUser);
    }

    @Test
    public void shouldBeDeleted() {
        userRepository.delete(2);
        Integer actualSize = userRepository.read().size();
        Assert.assertEquals((EXPECTED_SIZE), actualSize);
    }
}
