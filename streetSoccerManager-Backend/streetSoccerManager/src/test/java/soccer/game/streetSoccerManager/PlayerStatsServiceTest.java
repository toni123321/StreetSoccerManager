package soccer.game.streetSoccerManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import soccer.game.streetSoccerManager.model.PlayerStats;
import soccer.game.streetSoccerManager.model.Position;
import soccer.game.streetSoccerManager.repository.repositories.PlayerStats.PlayerStatsFakeDatabase;
import soccer.game.streetSoccerManager.repository.repositories.Position.PositionFakeDatabase;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPlayerStatsRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPositionRepository;
import soccer.game.streetSoccerManager.service.PlayerStatsService;
import soccer.game.streetSoccerManager.service.PositionService;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IPlayerStatsService;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IPositionService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class PlayerStatsServiceTest {
    @Test
    void GetAllPLayersStatsSuccessScenario() {
        // Arrange
        IPlayerStatsRepository playerStatsRepository = new PlayerStatsFakeDatabase();
        IPlayerStatsService playerStatsService = new PlayerStatsService(playerStatsRepository);

        // Act
        List<PlayerStats> playerStats = playerStatsService.getAll();
        playerStats = playerStats.stream().filter(stat -> stat.getId() < 2).collect(Collectors.toList());

        List<PlayerStats> playerStatsExpected = new ArrayList<>();
        playerStatsExpected.add(new PlayerStats(0l, 65));
        playerStatsExpected.add(new PlayerStats(1l, 60));

        // Assert
        Assertions.assertEquals(playerStatsExpected, playerStats);

    }

    @Test
    void GetPlayerStatsSuccessScenario() {
        // Arrange
        IPlayerStatsRepository playerStatsRepository = new PlayerStatsFakeDatabase();
        IPlayerStatsService playerStatsService = new PlayerStatsService(playerStatsRepository);

        // Act
        PlayerStats playerStats = playerStatsService.get(0l);
        // Assert
        Assertions.assertEquals(new PlayerStats(0l, 65), playerStats);
    }

    @Test
    void DeletePlayerStatsSuccessScenario(){
        // Arrange
        IPlayerStatsRepository playerStatsRepository = new PlayerStatsFakeDatabase();
        IPlayerStatsService playerStatsService = new PlayerStatsService(playerStatsRepository);

        // Act
        playerStatsService.delete(0l);
        List<PlayerStats> playerStats = playerStatsService.getAll();
        playerStats = playerStats.stream().filter(stat -> stat.getId() < 2).collect(Collectors.toList());

        List<PlayerStats> playerStatsExpected = new ArrayList<>();
        playerStatsExpected.add(new PlayerStats(0l, 65));
        playerStatsExpected.add(new PlayerStats(1l, 60));
        playerStatsExpected.remove(0);

        // Assert
        Assertions.assertEquals(playerStatsExpected, playerStats);
    }

    @Test
    void AddPlayerStatsSuccessScenario() {
        // Arrange
        IPlayerStatsRepository playerStatsRepository = new PlayerStatsFakeDatabase();
        IPlayerStatsService playerStatsService = new PlayerStatsService(playerStatsRepository);

        // Act
        playerStatsService.add(new PlayerStats(10l, 84));
        List<PlayerStats> playerStats = playerStatsService.getAll();
        playerStats = playerStats.stream().filter(stat -> stat.getId() < 2 || stat.getId().equals(10l)).collect(Collectors.toList());

        List<PlayerStats> playerStatsExpected = new ArrayList<>();
        playerStatsExpected.add(new PlayerStats(0l, 65));
        playerStatsExpected.add(new PlayerStats(1l, 60));
        playerStatsExpected.add(new PlayerStats(10l, 84));


        // Assert
        Assertions.assertEquals(playerStatsExpected, playerStats);
    }

    @Test
    void UpdatePlayerStatsSuccessScenario(){
        // Arrange
        IPlayerStatsRepository playerStatsRepository = new PlayerStatsFakeDatabase();
        IPlayerStatsService playerStatsService = new PlayerStatsService(playerStatsRepository);

        // Act
        playerStatsService.update(new PlayerStats(0l, 84));
        List<PlayerStats> playerStats = playerStatsService.getAll();
        playerStats = playerStats.stream().filter(stat -> stat.getId() < 2).collect(Collectors.toList());

        List<PlayerStats> playerStatsExpected = new ArrayList<>();
        playerStatsExpected.add(new PlayerStats(0l, 65));
        playerStatsExpected.add(new PlayerStats(1l, 60));
        playerStatsExpected.set(0, new PlayerStats(0l, 84));

        // Assert
        Assertions.assertEquals(playerStatsExpected, playerStats);
    }
}
