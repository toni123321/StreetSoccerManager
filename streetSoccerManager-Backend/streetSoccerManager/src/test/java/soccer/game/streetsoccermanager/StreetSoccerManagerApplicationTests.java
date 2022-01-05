package soccer.game.streetsoccermanager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class StreetSoccerManagerApplicationTests {
    @Test
    void exampleTest(){
        String test = "test";
        Assertions.assertEquals("test", test);
    }
}
