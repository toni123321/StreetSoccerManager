package soccer.game.streetsoccermanager.unit_tests;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import soccer.game.streetsoccermanager.model.entities.*;

@ActiveProfiles("test")
@SpringBootTest
class PlayMatchManagerUnitTest {

    Match match;

    @BeforeEach
    public void setUp() {

        match = new FriendlyMatch(1l, new CustomTeam(1l, "Eindhoven 19", new Formation(1l, "1-2-1"),
                new UserEntity(1l, "erick@gmail.com", "Erick_12345", "Erick", "Rodriguez", "Erick20", "USER")),
                new OfficialTeam(2l, "Barcelona", new Formation(2l, "2-1-1"), "Ronald Koeman"),
                "0:0", "Match started", 30);


    }
}
