package soccer.game.streetSoccerManager;

import org.junit.jupiter.api.Test;
import soccer.game.streetSoccerManager.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExampleTest {

    @Test
    public void testUserEmail()
    {
        // Arrange - Setup the code to be used
        UserService userService = new UserService();

        // Act - Execute the method to be tested
        String userEmail = userService.get(1).getEmail();

        // Assert - Check if the method postconditions is as expected
        assertEquals("peter@gmail.com", userEmail);
    }
}
