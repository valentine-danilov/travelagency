package repository;

import com.epam.travelagency.repository.UserRepository;
import com.epam.travelagency.storage.posgresql.UserDataContext;
import config.TestConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class UserRepositoryTest {
    @Autowired
    private UserDataContext dataContext;
    @Autowired
    private UserRepository userRepository;

    @Before
    public void init() {
        userRepository.setStorage(dataContext);
    }

    @Test
    public void shouldNotBeEmpty() {
        Assert.assertNotNull(userRepository.read(1));
    }
}
