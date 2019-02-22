package service;


import com.epam.travelagency.bean.User;
import com.epam.travelagency.repository.UserRepository;
import com.epam.travelagency.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private static final Integer INITIAL_ENTRY_NUMBER = 4;

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User testUser;

    @Before
    public void init() {
        testUser = new User();
        testUser.setId(1);
        testUser.setLogin("test");
        testUser.setPassword("");
        when(userRepository.read(ArgumentMatchers.anyInt())).thenReturn(testUser);
        when(userRepository.read()).thenReturn(List.of(new User(), new User(), new User(), new User()));
    }

    @Test
    public void shouldReadByIdCorrectly() {
        User actual = userService.readById(1);
        verify(userRepository).read(1);
        Assert.assertEquals(testUser, actual);
    }

    @Test
    public void shouldReadAllData() {
        List<User> actualUsers = userService.readAll();
        Integer actual = actualUsers.size();
        verify(userRepository).read();
        Assert.assertEquals(INITIAL_ENTRY_NUMBER, actual);
    }

    @Test
    public void deleteShouldBeInvoked() {
        userService.deleteById(1);
        verify(userRepository).delete(1);
    }

    @Test
    public void createShouldBeInvoked() {
        userService.add(testUser.getLogin(), testUser.getPassword());
        verify(userRepository).create(ArgumentMatchers.any(User.class));
    }

    @Test
    public void updateShouldBeInvoked() {
        userService.update(
                testUser.getId(),
                testUser.getLogin(),
                testUser.getPassword()
        );
        verify(userRepository).update(ArgumentMatchers.any(User.class));
    }
}
