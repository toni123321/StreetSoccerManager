package soccer.game.streetsoccermanager.integration_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import soccer.game.streetsoccermanager.model.entities.PlayerPersonalInfo;
import soccer.game.streetsoccermanager.service.PlayerPersonalInfoService;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
class PlayerPersonalInfoIntegrationTest {
    //Arrange
    @Autowired
    PlayerPersonalInfoService playerPersonalInfoService;
    List<PlayerPersonalInfo> playersPersonalInfo = new ArrayList<>();
    List<PlayerPersonalInfo> playersPersonalInfoExpected = new ArrayList<>();

    @BeforeEach
    void clearDB() {
        // Clear
        playerPersonalInfoService.deleteAll();
        playersPersonalInfoExpected.clear();

        // Add
        playerPersonalInfoService.add(new PlayerPersonalInfo("Lionel", "Messi", new GregorianCalendar(1997, 5, 15)));
        playerPersonalInfoService.add(new PlayerPersonalInfo("Cristiano", "Ronaldo", new GregorianCalendar(1995, 5, 15)));
        playersPersonalInfo = playerPersonalInfoService.getAll();
        playersPersonalInfoExpected.add(new PlayerPersonalInfo(playersPersonalInfo.get(0).getId(),  "Lionel", "Messi", new GregorianCalendar(1997, 5, 15)));
        playersPersonalInfoExpected.add(new PlayerPersonalInfo(playersPersonalInfo.get(1).getId(),  "Cristiano", "Ronaldo", new GregorianCalendar(1995, 5, 15)));
    }

    @Test
    void getAllPlayersPersonalInfoSuccessScenario() {
        // Assert
        Assertions.assertEquals(playersPersonalInfoExpected, playerPersonalInfoService.getAll()) ;
    }


    @Test
    void GetPlayerPersonalInfoSuccessScenario() {
        // Act
        PlayerPersonalInfo playerPersonalInfo = playerPersonalInfoService.get(playersPersonalInfo.get(0).getId());

        // Assert
        Assertions.assertEquals(new PlayerPersonalInfo(playersPersonalInfo.get(0).getId(),"Lionel", "Messi", new GregorianCalendar(1997, 5, 15)), playerPersonalInfo);
    }

    @Test
    void DeletePlayerPersonalInfoSuccessScenario(){
        // Act
        playerPersonalInfoService.delete(playersPersonalInfo.get(0).getId());
        playersPersonalInfoExpected.remove(0);

        // Assert
        Assertions.assertEquals(playersPersonalInfoExpected, playerPersonalInfoService.getAll());
    }

    @Test
    void AddPlayerPersonalInfoSuccessScenario() {
        // Act
        playerPersonalInfoService.add(new PlayerPersonalInfo("Juan", "Mata", new GregorianCalendar(1987, 5, 15)));
        playersPersonalInfo = playerPersonalInfoService.getAll();
        Long lastIndexId = playersPersonalInfo.get(playersPersonalInfo.size() - 1).getId();
        playersPersonalInfoExpected.add(new PlayerPersonalInfo(lastIndexId, "Juan", "Mata", new GregorianCalendar(1987, 5, 15)));

        // Assert
        Assertions.assertEquals(playersPersonalInfoExpected, playerPersonalInfoService.getAll());
    }

    @Test
    void UpdatePlayerPersonalInfoSuccessScenario(){
        // Act
        playerPersonalInfoService.update(new PlayerPersonalInfo(playersPersonalInfo.get(0).getId(), "Lionel", "Messi", new GregorianCalendar(1985, 5, 15)));
        playersPersonalInfoExpected.set(0, new PlayerPersonalInfo(playersPersonalInfo.get(0).getId(), "Lionel", "Messi", new GregorianCalendar(1985, 5, 15)));

        // Assert
        Assertions.assertEquals(playersPersonalInfoExpected, playerPersonalInfoService.getAll());
    }
}
