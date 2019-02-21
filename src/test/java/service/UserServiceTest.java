package service;

import com.epam.travelagency.bean.AppConfig;
import com.epam.travelagency.bean.User;
import com.epam.travelagency.repository.UserRepository;
import com.epam.travelagency.service.UserService;
import config.TestConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class UserServiceTest {

    @Autowired
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User testUser;

    @Before
    public void init(){
        testUser = new User();
        testUser.setId(1);
        testUser.setLogin("test");
        testUser.setPassword("");
        Mockito.when(userRepository.read(ArgumentMatchers.anyInt())).thenReturn(testUser);
        Mockito.when(userRepository.read()).thenReturn(List.of(new User(), new User(), new User(), new User()));
    }

    @Test
    public void shouldReadByIdCorrectly(){
        User actual = userService.readById(1);
        Assert.assertEquals(testUser, actual);
    }


}
