package soccer.game.streetSoccerManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import soccer.game.streetSoccerManager.model.entities.PlayerPersonalInfo;
import soccer.game.streetSoccerManager.model.entities.PlayerPositionInfo;
import soccer.game.streetSoccerManager.repository.repositories.PlayerPersonalInfo.PlayerPersonalInfoStubDatabase;
import soccer.game.streetSoccerManager.repository.repositories.PlayerPositionInfo.PlayerPositionInfoStubDatabase;
import soccer.game.streetSoccerManager.repository.repositories.Position.PositionStubDatabase;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPlayerPersonalInfoRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPlayerPositionInfoRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPositionRepository;
import soccer.game.streetSoccerManager.service.PlayerPersonalInfoService;
import soccer.game.streetSoccerManager.service.PlayerPositionInfoService;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IPlayerPersonalInfoService;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IPlayerPositionInfoService;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@SpringBootTest
public class PlayerPositionInfoServiceTest {
    @Test
    void GetAllPlayersPositionInfoSuccessScenario() {
        // Arrange
        IPlayerPositionInfoRepository playerPositionInfoRepository = new PlayerPositionInfoStubDatabase();
        IPlayerPositionInfoService playerPositionInfoService = new PlayerPositionInfoService(playerPositionInfoRepository);
        IPositionRepository positionRepository = new PositionStubDatabase();
        // Act
        List<PlayerPositionInfo> playersPositionInfo = playerPositionInfoService.getAll().subList(0, 2);

        List<PlayerPositionInfo> playersPositionInfoExpected = new ArrayList<>();
        playersPositionInfoExpected.add(new PlayerPositionInfo(1l, 11, positionRepository.get(10l), positionRepository.get(10l), true));
        playersPositionInfoExpected.add(new PlayerPositionInfo(2l, 8, positionRepository.get(8l), positionRepository.get(8l), true));


        // Assert
        Assertions.assertEquals(playersPositionInfoExpected, playersPositionInfo);

    }

    @Test
    void GetPlayerPositionInfoSuccessScenario() {
        // Arrange
        IPlayerPositionInfoRepository playerPositionInfoRepository = new PlayerPositionInfoStubDatabase();
        IPlayerPositionInfoService playerPositionInfoService = new PlayerPositionInfoService(playerPositionInfoRepository);
        IPositionRepository positionRepository = new PositionStubDatabase();
        // Act
        PlayerPositionInfo playerPositionInfo = playerPositionInfoService.get(1l);

        // Assert
        Assertions.assertEquals(new PlayerPositionInfo(1l, 11, positionRepository.get(10l), positionRepository.get(10l), true), playerPositionInfo);
    }

    @Test
    void DeletePlayerPositionInfoSuccessScenario() {
        // Arrange
        IPlayerPositionInfoRepository playerPositionInfoRepository = new PlayerPositionInfoStubDatabase();
        IPlayerPositionInfoService playerPositionInfoService = new PlayerPositionInfoService(playerPositionInfoRepository);
        IPositionRepository positionRepository = new PositionStubDatabase();
        // Act
        playerPositionInfoService.delete(1l);
        List<PlayerPositionInfo> playersPositionInfo = playerPositionInfoService.getAll().subList(0, 1);

        List<PlayerPositionInfo> playersPositionInfoExpected = new ArrayList<>();
        playersPositionInfoExpected.add(new PlayerPositionInfo(1l, 11, positionRepository.get(10l), positionRepository.get(10l), true));
        playersPositionInfoExpected.add(new PlayerPositionInfo(2l, 8, positionRepository.get(8l), positionRepository.get(8l), true));
        playersPositionInfoExpected.remove(0);

        // Assert
        Assertions.assertEquals(playersPositionInfoExpected, playersPositionInfo);
    }

    @Test
    void AddPlayerPositionInfoSuccessScenario() {
        // Arrange
        IPlayerPositionInfoRepository playerPositionInfoRepository = new PlayerPositionInfoStubDatabase();
        IPlayerPositionInfoService playerPositionInfoService = new PlayerPositionInfoService(playerPositionInfoRepository);
        IPositionRepository positionRepository = new PositionStubDatabase();
        // Act
        playerPositionInfoService.add(new PlayerPositionInfo(4l, 10, positionRepository.get(4l), positionRepository.get(8l), true));
        List<PlayerPositionInfo> playersPositionInfo = playerPositionInfoService.getAll().subList(playerPositionInfoService.getAll().size()-2, playerPositionInfoService.getAll().size());
        //playersPositionInfo.add(playerPositionInfoService.getAll().get(playerPositionInfoService.getAll().size() - 1));

        List<PlayerPositionInfo> playersPositionInfoExpected = new ArrayList<>();
        playersPositionInfoExpected.add(new PlayerPositionInfo(3l, 17, positionRepository.get(2l), positionRepository.get(15l), false));
        playersPositionInfoExpected.add(new PlayerPositionInfo(4l, 10, positionRepository.get(4l), positionRepository.get(8l), true));


        // Assert
        Assertions.assertEquals(playersPositionInfoExpected, playersPositionInfo);
    }

    @Test
    void UpdatePlayerPositionInfoSuccessScenario(){
        // Arrange
        IPlayerPositionInfoRepository playerPositionInfoRepository = new PlayerPositionInfoStubDatabase();
        IPlayerPositionInfoService playerPositionInfoService = new PlayerPositionInfoService(playerPositionInfoRepository);
        IPositionRepository positionRepository = new PositionStubDatabase();
        // Act
        playerPositionInfoService.update(new PlayerPositionInfo(1l, 1, positionRepository.get(10l), positionRepository.get(10l), true));
        List<PlayerPositionInfo> playersPositionInfo = playerPositionInfoService.getAll().subList(0, 2);

        List<PlayerPositionInfo> playersPositionInfoExpected = new ArrayList<>();
        playersPositionInfoExpected.add(new PlayerPositionInfo(1l, 11, positionRepository.get(10l), positionRepository.get(10l), true));
        playersPositionInfoExpected.add(new PlayerPositionInfo(2l, 8, positionRepository.get(8l), positionRepository.get(8l), true));
        playersPositionInfoExpected.set(0, new PlayerPositionInfo(1l, 1, positionRepository.get(10l), positionRepository.get(10l), true));

        // Assert
        Assertions.assertEquals(playersPositionInfoExpected, playersPositionInfo);

    }
}
