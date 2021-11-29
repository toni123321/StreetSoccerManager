package soccer.game.streetsocceranager.integration_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import soccer.game.streetsocceranager.model.entities.*;
import soccer.game.streetsocceranager.service.PlayerAdditionalInfoService;
import soccer.game.streetsocceranager.service.PlayerStatsService;

import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
public class PlayerAdditionalInfoIntegrationTest {
    //Arrange
    @Autowired
    PlayerAdditionalInfoService playerAdditionalInfoService;
    List<PlayerAdditionalInfo> playersAdditionalInfo = new ArrayList<>();
    List<PlayerAdditionalInfo> playersAdditionalInfoExpected = new ArrayList<>();
    @Autowired
    PlayerStatsService playerStatsService;

    @BeforeEach
    void clearDB() {
        // Clear
        playerAdditionalInfoService.deleteAll();
        playerStatsService.deleteAll();

        playersAdditionalInfoExpected.clear();

        // Add
        playerStatsService.add(new PlayerStats(60, 70));
        playerStatsService.add(new PlayerStats(70, 75));

        playerAdditionalInfoService.add(new PlayerAdditionalInfo(150, playerStatsService.getAll().get(0)));
        playerAdditionalInfoService.add(new PlayerAdditionalInfo(120, playerStatsService.getAll().get(1)));
        playersAdditionalInfo = playerAdditionalInfoService.getAll();
        playersAdditionalInfoExpected.add(new PlayerAdditionalInfo(playersAdditionalInfo.get(0).getId(), 150, playerStatsService.getAll().get(0)));
        playersAdditionalInfoExpected.add(new PlayerAdditionalInfo(playersAdditionalInfo.get(1).getId(),  120, playerStatsService.getAll().get(1)));
    }

    @Test
    void getAllPlayersAdditionalInfoSuccessScenario() {
        // Assert
        Assertions.assertEquals(playersAdditionalInfoExpected, playerAdditionalInfoService.getAll()) ;
    }


    @Test
    void GetPlayerAdditionalInfoSuccessScenario() {
        // Act
        PlayerAdditionalInfo playerAdditionalInfo = playerAdditionalInfoService.get(playersAdditionalInfo.get(0).getId());

        // Assert
        Assertions.assertEquals(new PlayerAdditionalInfo(playersAdditionalInfo.get(0).getId(), 150, playerStatsService.getAll().get(0)), playerAdditionalInfo);
    }

    @Test
    void DeletePlayerAdditionalInfoSuccessScenario(){
        // Act
        playerAdditionalInfoService.delete(playersAdditionalInfo.get(0).getId());
        playersAdditionalInfoExpected.remove(0);

        // Assert
        Assertions.assertEquals(playersAdditionalInfoExpected, playerAdditionalInfoService.getAll());
    }

    @Test
    void AddPlayerAdditionalInfoSuccessScenario() {
        // Act
        playerStatsService.add(new PlayerStats(58, 79));
        playerAdditionalInfoService.add(new PlayerAdditionalInfo(200, playerStatsService.getAll().get(2)));
        playersAdditionalInfo = playerAdditionalInfoService.getAll();
        Long lastIndexId = playersAdditionalInfo.get(playersAdditionalInfo.size() - 1).getId();
        playersAdditionalInfoExpected.add(new PlayerAdditionalInfo(lastIndexId, 200, playerStatsService.getAll().get(2)));

        // Assert
        Assertions.assertEquals(playersAdditionalInfoExpected, playerAdditionalInfoService.getAll());
    }

    @Test
    void UpdatePlayerAdditionalInfoSuccessScenario(){
        // Act
        playerAdditionalInfoService.update(new PlayerAdditionalInfo(playersAdditionalInfo.get(0).getId(), 159, playerStatsService.getAll().get(0)));
        playersAdditionalInfoExpected.set(0, new PlayerAdditionalInfo(playersAdditionalInfo.get(0).getId(), 159, playerStatsService.getAll().get(0)));

        // Assert
        Assertions.assertEquals(playersAdditionalInfoExpected, playerAdditionalInfoService.getAll());
    }
}
