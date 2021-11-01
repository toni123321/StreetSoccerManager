package soccer.game.streetSoccerManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import soccer.game.streetSoccerManager.model.entities.Admin;
import soccer.game.streetSoccerManager.model.entities.EndUser;
import soccer.game.streetSoccerManager.model.entities.User;
import soccer.game.streetSoccerManager.repository.repositories.User.UserStubDatabase;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IUserRepository;
import soccer.game.streetSoccerManager.service.UserService;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IUserService;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserServiceTest {
    @Test
    void GetAllUsersSuccessScenario() {
        // Arrange
        IUserRepository userRepository = new UserStubDatabase();
        IUserService userService = new UserService(userRepository);

        // Act
        List<User> users = userService.getAll();

        List<User> usersExpected = new ArrayList<>();
        usersExpected.add(new EndUser(0l, "peter@gmail.com", "123", "pete", 100));
        usersExpected.add(new EndUser(1l, "john@gmail.com", "456", "jo", 10));
        usersExpected.add(new Admin(2l, "admin1@gmail.com", "admin1", "Admin1", "Admin1"));


        // Assert
        Assertions.assertEquals(usersExpected, users);

    }

    @Test
    void GetUserSuccessScenario() {
        // Arrange
        IUserRepository userRepository = new UserStubDatabase();
        IUserService userService = new UserService(userRepository);

        // Act
        User user = userService.get(0l);

        // Assert
        Assertions.assertEquals(new EndUser(0l, "peter@gmail.com", "123", "pete", 100), user);
    }

    @Test
    void DeleteUserSuccessScenario() {
        // Arrange
        IUserRepository userRepository = new UserStubDatabase();
        IUserService userService = new UserService(userRepository);

        // Act
        userService.delete(0l);
        List<User> users = userService.getAll();

        List<User> usersExpected = new ArrayList<>();
        usersExpected.add(new EndUser(0l, "peter@gmail.com", "123", "pete", 100));
        usersExpected.add(new EndUser(1l, "john@gmail.com", "456", "jo", 10));
        usersExpected.add(new Admin(2l, "admin1@gmail.com", "admin1", "Admin1", "Admin1"));
        usersExpected.remove(0);

        // Assert
        Assertions.assertEquals(usersExpected, users);
    }

    @Test
    void AddUserSuccessScenario() {
        // Arrange
        IUserRepository userRepository = new UserStubDatabase();
        IUserService userService = new UserService(userRepository);

        // Act
        userService.add(new EndUser(3l, "george@gmail.com", "324", "george", 25));
        List<User> users = userService.getAll();

        List<User> usersExpected = new ArrayList<>();
        usersExpected.add(new EndUser(0l, "peter@gmail.com", "123", "pete", 100));
        usersExpected.add(new EndUser(1l, "john@gmail.com", "456", "jo", 10));
        usersExpected.add(new Admin(2l, "admin1@gmail.com", "admin1", "Admin1", "Admin1"));
        usersExpected.add(new EndUser(3l, "george@gmail.com", "324", "george", 25));

        // Assert
        Assertions.assertEquals(usersExpected, users);
    }

    @Test
    void UpdateUserSuccessScenario(){
        // Arrange
        IUserRepository userRepository = new UserStubDatabase();
        IUserService userService = new UserService(userRepository);

        // Act
        userService.update(new EndUser(0l, "george@gmail.com", "324", "george", 25));
        List<User> users = userService.getAll();

        List<User> usersExpected = new ArrayList<>();
        usersExpected.add(new EndUser(0l, "peter@gmail.com", "123", "pete", 100));
        usersExpected.add(new EndUser(1l, "john@gmail.com", "456", "jo", 10));
        usersExpected.add(new Admin(2l, "admin1@gmail.com", "admin1", "Admin1", "Admin1"));
        usersExpected.set(0, new EndUser(0l, "george@gmail.com", "324", "george", 25));

        // Assert
        Assertions.assertEquals(usersExpected, users);
    }
}
