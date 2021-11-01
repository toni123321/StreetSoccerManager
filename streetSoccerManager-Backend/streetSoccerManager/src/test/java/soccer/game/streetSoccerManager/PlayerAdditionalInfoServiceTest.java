package soccer.game.streetSoccerManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import soccer.game.streetSoccerManager.model.entities.PlayerAdditionalInfo;
import soccer.game.streetSoccerManager.model.entities.PlayerPersonalInfo;
import soccer.game.streetSoccerManager.repository.repositories.PlayerAdditionalInfo.PlayerAdditionalInfoFakeDatabase;
import soccer.game.streetSoccerManager.repository.repositories.PlayerPersonalInfo.PlayerPersonalInfoFakeDatabase;
import soccer.game.streetSoccerManager.repository.repositories.PlayerStats.PlayerStatsFakeDatabase;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPlayerAdditionalInfoRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPlayerPersonalInfoRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPlayerStatsRepository;
import soccer.game.streetSoccerManager.service.PlayerAdditionalInfoService;
import soccer.game.streetSoccerManager.service.PlayerPersonalInfoService;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IPlayerAdditionalInfoService;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IPlayerPersonalInfoService;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@SpringBootTest
public class PlayerAdditionalInfoServiceTest {
    @Test
    void GetAllPlayersAdditionalInfoSuccessScenario() {
        // Arrange
        IPlayerAdditionalInfoRepository playerAdditionalInfoRepository = new PlayerAdditionalInfoFakeDatabase();
        IPlayerAdditionalInfoService playerAdditionalInfoService = new PlayerAdditionalInfoService(playerAdditionalInfoRepository);
        IPlayerStatsRepository playersStatsRepository = new PlayerStatsFakeDatabase();

        // Act
        List<PlayerAdditionalInfo> playersAdditionalInfo = playerAdditionalInfoService.getAll();

        List<PlayerAdditionalInfo> playersAdditionalInfoExpected = new ArrayList<>();
        playersAdditionalInfoExpected.add(new PlayerAdditionalInfo(1l, 150, playersStatsRepository.get(0l)));
        playersAdditionalInfoExpected.add(new PlayerAdditionalInfo(2l, 120, playersStatsRepository.get(1l)));


        // Assert
        Assertions.assertEquals(playersAdditionalInfoExpected, playersAdditionalInfo);

    }

    @Test
    void GetPlayerAdditionalInfoSuccessScenario() {
        // Arrange
        IPlayerAdditionalInfoRepository playerAdditionalInfoRepository = new PlayerAdditionalInfoFakeDatabase();
        IPlayerAdditionalInfoService playerAdditionalInfoService = new PlayerAdditionalInfoService(playerAdditionalInfoRepository);
        IPlayerStatsRepository playersStatsRepository = new PlayerStatsFakeDatabase();

        // Act
        PlayerAdditionalInfo playerAdditionalInfo = playerAdditionalInfoService.get(1l);

        // Assert
        Assertions.assertEquals(new PlayerAdditionalInfo(1l, 150, playersStatsRepository.get(0l)), playerAdditionalInfo);
    }

    @Test
    void DeletePlayerAdditionalInfoSuccessScenario() {
        // Arrange
        IPlayerAdditionalInfoRepository playerAdditionalInfoRepository = new PlayerAdditionalInfoFakeDatabase();
        IPlayerAdditionalInfoService playerAdditionalInfoService = new PlayerAdditionalInfoService(playerAdditionalInfoRepository);
        IPlayerStatsRepository playersStatsRepository = new PlayerStatsFakeDatabase();

        // Act
        playerAdditionalInfoService.delete(1l);
        List<PlayerAdditionalInfo> playersAdditionalInfo = playerAdditionalInfoService.getAll();

        List<PlayerAdditionalInfo> playersAdditionalInfoExpected = new ArrayList<>();
        playersAdditionalInfoExpected.add(new PlayerAdditionalInfo(1l, 150, playersStatsRepository.get(0l)));
        playersAdditionalInfoExpected.add(new PlayerAdditionalInfo(2l, 120, playersStatsRepository.get(1l)));
        playersAdditionalInfoExpected.remove(0);

        // Assert
        Assertions.assertEquals(playersAdditionalInfoExpected, playersAdditionalInfo);

    }

    @Test
    void AddPlayerAdditionalInfoSuccessScenario() {
        // Arrange
        IPlayerAdditionalInfoRepository playerAdditionalInfoRepository = new PlayerAdditionalInfoFakeDatabase();
        IPlayerAdditionalInfoService playerAdditionalInfoService = new PlayerAdditionalInfoService(playerAdditionalInfoRepository);
        IPlayerStatsRepository playersStatsRepository = new PlayerStatsFakeDatabase();

        // Act
        playerAdditionalInfoService.add(new PlayerAdditionalInfo(3l, 180, playersStatsRepository.get(0l)));
        List<PlayerAdditionalInfo> playersAdditionalInfo = playerAdditionalInfoService.getAll();

        List<PlayerAdditionalInfo> playersAdditionalInfoExpected = new ArrayList<>();
        playersAdditionalInfoExpected.add(new PlayerAdditionalInfo(1l, 150, playersStatsRepository.get(0l)));
        playersAdditionalInfoExpected.add(new PlayerAdditionalInfo(2l, 120, playersStatsRepository.get(1l)));
        playersAdditionalInfoExpected.add(new PlayerAdditionalInfo(3l, 180, playersStatsRepository.get(0l)));

        // Assert
        Assertions.assertEquals(playersAdditionalInfoExpected, playersAdditionalInfo);


    }

    @Test
    void UpdatePlayerAdditionalInfoSuccessScenario(){
        // Arrange
        IPlayerAdditionalInfoRepository playerAdditionalInfoRepository = new PlayerAdditionalInfoFakeDatabase();
        IPlayerAdditionalInfoService playerAdditionalInfoService = new PlayerAdditionalInfoService(playerAdditionalInfoRepository);
        IPlayerStatsRepository playersStatsRepository = new PlayerStatsFakeDatabase();

        // Act
        playerAdditionalInfoService.update(new PlayerAdditionalInfo(2l, 180, playersStatsRepository.get(0l)));
        List<PlayerAdditionalInfo> playersAdditionalInfo = playerAdditionalInfoService.getAll();

        List<PlayerAdditionalInfo> playersAdditionalInfoExpected = new ArrayList<>();
        playersAdditionalInfoExpected.add(new PlayerAdditionalInfo(1l, 150, playersStatsRepository.get(0l)));
        playersAdditionalInfoExpected.add(new PlayerAdditionalInfo(2l, 120, playersStatsRepository.get(1l)));
        playersAdditionalInfoExpected.set(1, new PlayerAdditionalInfo(2l, 180, playersStatsRepository.get(0l)));

        // Assert
        Assertions.assertEquals(playersAdditionalInfoExpected, playersAdditionalInfo);
    }
}
