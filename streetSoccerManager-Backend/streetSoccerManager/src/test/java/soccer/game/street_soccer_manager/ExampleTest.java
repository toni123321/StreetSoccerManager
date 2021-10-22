package soccer.game.street_soccer_manager;

import org.junit.jupiter.api.Test;
import soccer.game.street_soccer_manager.repository.repositories.User.UserFakeDatabase;
import soccer.game.street_soccer_manager.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExampleTest {

    @Test
    public void testUserEmail()
    {
        // Arrange - Setup the code to be used

        UserService userService = new UserService(new UserFakeDatabase());

        // Act - Execute the method to be tested
        String userEmail = userService.get(0l).getEmail();

        // Assert - Check if the method postconditions is as expected
        assertEquals("peter@gmail.com", userEmail);
    }
}
