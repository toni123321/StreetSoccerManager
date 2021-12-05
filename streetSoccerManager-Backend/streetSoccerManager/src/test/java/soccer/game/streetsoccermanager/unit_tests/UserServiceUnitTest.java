package soccer.game.streetsoccermanager.unit_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import soccer.game.streetsoccermanager.model.entities.UserEntity;
import soccer.game.streetsoccermanager.repository_interfaces.IUserRepository;
import soccer.game.streetsoccermanager.service.UserService;
import soccer.game.streetsoccermanager.service_interfaces.IUserService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
class UserServiceUnitTest {
    @Mock
    IUserRepository userRepository;
    IUserService userService;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @BeforeEach
    public void setUp()  {
        List<UserEntity> users = List.of(
                new UserEntity(1l, "erick@gmail.com", "erick12345", "Erick", "Rodriguez", "Erick20", "USER", 0),
                new UserEntity(2l, "john@gmail.com", "johnTravel", "John", "Henman", "Jo", "ADMIN", 0)
        );
        when(userRepository.getAll()).thenReturn(users);
        when(userRepository.get(1l)).thenReturn(users.get(0));
        when(userRepository.get(2l)).thenReturn(users.get(1));
        when(userRepository.add(new UserEntity("peter@gmail.com", "jj12", "Peter", "Petrov", "Pesho", "USER", 0))).
                thenReturn(new UserEntity(3l,"peter@gmail.com", passwordEncoder.encode( "jj12"), "Peter", "Petrov", "Pesho", "USER", 0));

        when(userRepository.update(new UserEntity(2l, "john@gmail.com", "johnTravel", "John", "Henman", "Jo@travel", "ADMIN", 0))).
                thenReturn(new UserEntity(2l, "john@gmail.com", "johnTravel", "John", "Henman", "Jo@travel", "ADMIN", 0));
        userService = new UserService(userRepository);
    }

    @Test
    void GetAllUsersSuccessScenario() {
        // Act
        List<UserEntity> users = userService.getAll();

        List<UserEntity> usersExpected = new ArrayList<>();
        usersExpected.add(new UserEntity(1l, "erick@gmail.com", "erick12345", "Erick", "Rodriguez", "Erick20", "USER", 0));
        usersExpected.add(new UserEntity(2l, "john@gmail.com", "johnTravel", "John", "Henman", "Jo", "ADMIN", 0));

        // Assert
        Assertions.assertEquals(usersExpected, users);
    }

    @Test
    void GetUserSuccessScenario() {
        // Act
        UserEntity user = userService.get(1l);

        // Assert
        Assertions.assertEquals(new UserEntity(1l, "erick@gmail.com", "erick12345", "Erick", "Rodriguez", "Erick20", "USER", 0), user);
    }

    @Test
    void DeleteUserSuccessScenario(){
        // Act
        userService.delete(1l);
        verify(userRepository).delete(1l);
        when(userRepository.delete(2l)).thenReturn(true);
        Assertions.assertEquals(true, userService.delete(2l));
        when(userRepository.get(3l)).thenReturn(null);
        when(userRepository.delete(3l)).thenReturn(false);
        Assertions.assertEquals(false, userService.delete(3l));
    }

    @Test
    void AddUserSuccessScenario() {
        // Act
        UserEntity newUser = userService.add(new UserEntity("peter@gmail.com", "jj12", "Peter", "Petrov", "Pesho", "USER", 0));
        // Assert
        Assertions.assertEquals(new UserEntity(3l,"peter@gmail.com", passwordEncoder.encode("jj12"), "Peter", "Petrov", "Pesho", "USER", 0), newUser);
        Assertions.assertEquals(null, userService.add(new UserEntity(3l, "peter@gmail.com", "jj12", "Peter", "Petrov", "Pesho", "USER", 0)));
    }

    @Test
    void UpdateUserSuccessScenario(){
        // Act
        UserEntity updatedUser = userService.update(new UserEntity(2l, "john@gmail.com", "johnTravel", "John", "Henman", "Jo@travel", "ADMIN", 0));
        // Assert
        Assertions.assertEquals(new UserEntity(2l, "john@gmail.com", "johnTravel", "John", "Henman", "Jo@travel", "ADMIN", 0), updatedUser);
        Assertions.assertEquals(null, userService.update(new UserEntity( "john@gmail.com", "johnTravel", "John", "Henman", "Jo@travel", "ADMIN", 0)));
    }
}
