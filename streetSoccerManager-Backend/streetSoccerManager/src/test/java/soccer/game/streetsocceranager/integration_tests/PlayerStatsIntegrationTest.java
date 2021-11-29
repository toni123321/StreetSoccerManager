package soccer.game.streetsocceranager.integration_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import soccer.game.streetsocceranager.model.entities.PlayerStats;
import soccer.game.streetsocceranager.service.PlayerStatsService;

import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
class PlayerStatsIntegrationTest {
    //Arrange
    @Autowired
    PlayerStatsService playerStatsService;
    List<PlayerStats> playersStats = new ArrayList<>();
    List<PlayerStats> playersStatsExpected = new ArrayList<>();

    @BeforeEach
    void clearDB() {
        // Clear
        playerStatsService.deleteAll();
        playersStatsExpected.clear();

        // Add
        playerStatsService.add(new PlayerStats(60, 70));
        playerStatsService.add(new PlayerStats(70, 80));
        playersStats = playerStatsService.getAll();
        playersStatsExpected.add(new PlayerStats(playersStats.get(0).getId(),  60, 70));
        playersStatsExpected.add(new PlayerStats(playersStats.get(1).getId(),  70, 80));
    }

    @Test
    void getAllPlayersStatsSuccessScenario() {
        // Assert
        Assertions.assertEquals(playersStatsExpected, playerStatsService.getAll()) ;
    }


    @Test
    void GetPlayerStatsSuccessScenario() {
        // Act
        PlayerStats playerStats = playerStatsService.get(playersStats.get(0).getId());

        // Assert
        Assertions.assertEquals(new PlayerStats(playersStats.get(0).getId(), 60, 70), playerStats);
    }

    @Test
    void DeletePlayerStatsSuccessScenario(){
        // Act
        playerStatsService.delete(playersStats.get(0).getId());
        playersStatsExpected.remove(0);

        // Assert
        Assertions.assertEquals(playersStatsExpected, playerStatsService.getAll());
    }

    @Test
    void AddPlayerStatsSuccessScenario() {
        // Act
        playerStatsService.add(new PlayerStats(75, 65));
        playersStats = playerStatsService.getAll();
        Long lastIndexId = playersStats.get(playersStats.size() - 1).getId();
        playersStatsExpected.add(new PlayerStats(lastIndexId, 75, 65));

        // Assert
        Assertions.assertEquals(playersStatsExpected, playerStatsService.getAll());
    }

    @Test
    void UpdatePlayerStatsSuccessScenario(){
        // Act
        playerStatsService.update(new PlayerStats(playersStats.get(0).getId(), 65, 70));
        playersStatsExpected.set(0, new PlayerStats(playersStats.get(0).getId(), 65, 70));

        // Assert
        Assertions.assertEquals(playersStatsExpected, playerStatsService.getAll());
    }
}
