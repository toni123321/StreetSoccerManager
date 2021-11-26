package soccer.game.streetSoccerManager.unit_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import soccer.game.streetSoccerManager.model.entities.Formation;
import soccer.game.streetSoccerManager.model.entities.Player;
import soccer.game.streetSoccerManager.model.entities.PlayerStats;
import soccer.game.streetSoccerManager.repository_interfaces.IFormationRepository;
import soccer.game.streetSoccerManager.repository_interfaces.IPlayerStatsRepository;
import soccer.game.streetSoccerManager.service.FormationService;
import soccer.game.streetSoccerManager.service.PlayerStatsService;
import soccer.game.streetSoccerManager.service_interfaces.IFormationService;
import soccer.game.streetSoccerManager.service_interfaces.IPlayerStatsService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
public class PlayerStatsServiceUnitTest {
    @Mock
    IPlayerStatsRepository playerStatsRepository;
    IPlayerStatsService playerStatsService;

    @BeforeEach
    public void setUp()  {
        List<PlayerStats> playersStats = List.of(
                new PlayerStats(1l, 60, 60),
                new PlayerStats(2l, 78, 80)
        );
        when(playerStatsRepository.getAll()).thenReturn(playersStats);
        when(playerStatsRepository.get(1l)).thenReturn(playersStats.get(0));
        when(playerStatsRepository.get(2l)).thenReturn(playersStats.get(1));
        when(playerStatsRepository.add(new PlayerStats(80, 83))).thenReturn(new PlayerStats(3l, 80, 83));
        when(playerStatsRepository.update(new PlayerStats(2l, 68, 80))).thenReturn(new PlayerStats(2l, 68, 80));
        playerStatsService = new PlayerStatsService(playerStatsRepository);
    }

    @Test
    void GetAllPlayersStatsSuccessScenario() {
        // Act
        List<PlayerStats> playersStats = playerStatsService.getAll();

        List<PlayerStats> playersStatsExpected = new ArrayList<>();
        playersStatsExpected.add(new PlayerStats(1l, 60, 60));
        playersStatsExpected.add(new PlayerStats(2l, 78, 80));

        // Assert
        Assertions.assertEquals(playersStatsExpected, playersStats);
    }

    @Test
    void GetPlayerStatsSuccessScenario() {
        // Act
        PlayerStats playerStats = playerStatsService.get(1l);

        // Assert
        Assertions.assertEquals(new PlayerStats(1l, 60, 60), playerStats);
    }

    @Test
    void DeletePlayerStatsSuccessScenario(){
        // Act
        playerStatsService.delete(1l);
        verify(playerStatsRepository).delete(1l);
        when(playerStatsRepository.delete(2l)).thenReturn(true);
        Assertions.assertEquals(true, playerStatsService.delete(2l));
        when(playerStatsRepository.get(3l)).thenReturn(null);
        when(playerStatsRepository.delete(3l)).thenReturn(false);
        Assertions.assertEquals(false, playerStatsService.delete(3l));
    }

    @Test
    void AddPlayerStatsSuccessScenario() {
        // Act
        PlayerStats newPlayerStats = playerStatsService.add(new PlayerStats(80, 83));
        // Assert
        Assertions.assertEquals(new PlayerStats(3l, 80, 83), newPlayerStats);
        Assertions.assertEquals(null, playerStatsService.add(new PlayerStats(3l, 80, 83)));
    }

    @Test
    void UpdatePlayerStatsSuccessScenario(){
        // Act
        PlayerStats updatedPlayerStats = playerStatsService.update(new PlayerStats(2l, 68, 80));
        // Assert
        Assertions.assertEquals(new PlayerStats(2l, 68, 80), updatedPlayerStats);
        Assertions.assertEquals(null, playerStatsService.update(new PlayerStats(68, 80)));
    }
}
