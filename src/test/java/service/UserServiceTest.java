package service;

import com.epam.travelagency.bean.User;
import com.epam.travelagency.repository.UserRepository;
import com.epam.travelagency.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

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
