package repository;

import com.epam.travelagency.bean.User;
import com.epam.travelagency.repository.UserRepository;
import com.epam.travelagency.storage.posgresql.UserDataContext;
import com.opentable.db.postgres.embedded.FlywayPreparer;
import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.PreparedDbRule;
import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserRepositoryTest {

    @ClassRule
    public static PreparedDbRule db =
            EmbeddedPostgresRules.preparedDatabase(
                    FlywayPreparer.forClasspathLocation("db/migration"));

    private UserRepository userRepository;

    private static final Integer EXPECTED_SIZE = 4;

    private User testUser = new User();

    @Before
    public void init() {
        JdbcTemplate template = new JdbcTemplate(db.getTestDatabase());
        UserDataContext dataContext = new UserDataContext(template);
        userRepository = new UserRepository();
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
        Integer generatedId = userRepository.create(testUser);
        Assert.assertEquals(userRepository.read(generatedId), testUser);
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
