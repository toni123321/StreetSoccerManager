package soccer.game.streetsoccermanager.unit_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import soccer.game.streetsoccermanager.model.entities.UserEntity;
import soccer.game.streetsoccermanager.repository_interfaces.IUserRepository;
import soccer.game.streetsoccermanager.service.AuthenticationUserDetailService;
import soccer.game.streetsoccermanager.service.UserService;
import soccer.game.streetsoccermanager.service_interfaces.IUserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
class AuthenticationUserDetailServiceUnitTest {


    AuthenticationUserDetailService authenticationUserDetailService;
    @Mock
    IUserRepository userRepository;
    @Mock
    IUserService userService;
    UserEntity mockedUser;

    @BeforeEach
    public void setUp()  {
        UserEntity user =
                new UserEntity(1l, "erick@gmail.com", "Erick_12345", "Erick", "Rodriguez", "Erick20", "USER");
        when(userRepository.getByEmail("erick@gmail.com")).thenReturn(user);
        mockedUser = user;
        userService = new UserService(userRepository);
        authenticationUserDetailService = new AuthenticationUserDetailService(userService);

    }

    @Test
    void LoadByUsername() {
        UserDetails user = authenticationUserDetailService.loadUserByUsername("erick@gmail.com");
        Assertions.assertEquals(
                new User(mockedUser.getEmail(), mockedUser.getPassword(),  Arrays.asList(new SimpleGrantedAuthority(mockedUser.getRole()))),
                user);
        Throwable exception = assertThrows(UsernameNotFoundException.class, () -> authenticationUserDetailService.loadUserByUsername("john@gmail.com"));
        Assertions.assertEquals("john@gmail.com", exception.getMessage());

    }
}
