//package soccer.game.streetSoccerManager;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import soccer.game.streetSoccerManager.model.entities.PlayerAdditionalInfo;
//import soccer.game.streetSoccerManager.repository.PlayerAdditionalInfo.PlayerAdditionalInfoStubDatabase;
//import soccer.game.streetSoccerManager.repository.PlayerStats.PlayerStatsStubDatabase;
//import soccer.game.streetSoccerManager.repository_interfaces.IPlayerAdditionalInfoRepository;
//import soccer.game.streetSoccerManager.repository_interfaces.IPlayerStatsRepository;
//import soccer.game.streetSoccerManager.service.PlayerAdditionalInfoService;
//import soccer.game.streetSoccerManager.service_interfaces.IPlayerAdditionalInfoService;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@SpringBootTest
//public class PlayerAdditionalInfoServiceTest {
//    @Test
//    void GetAllPlayersAdditionalInfoSuccessScenario() {
//        // Arrange
//        IPlayerAdditionalInfoRepository playerAdditionalInfoRepository = new PlayerAdditionalInfoStubDatabase();
//        IPlayerAdditionalInfoService playerAdditionalInfoService = new PlayerAdditionalInfoService(playerAdditionalInfoRepository);
//        IPlayerStatsRepository playersStatsRepository = new PlayerStatsStubDatabase();
//
//        // Act
//        List<PlayerAdditionalInfo> playersAdditionalInfo = playerAdditionalInfoService.getAll();
//
//        List<PlayerAdditionalInfo> playersAdditionalInfoExpected = new ArrayList<>();
//        playersAdditionalInfoExpected.add(new PlayerAdditionalInfo(1l, 150, playersStatsRepository.get(0l)));
//        playersAdditionalInfoExpected.add(new PlayerAdditionalInfo(2l, 120, playersStatsRepository.get(1l)));
//
//
//        // Assert
//        Assertions.assertEquals(playersAdditionalInfoExpected, playersAdditionalInfo);
//
//    }
//
//    @Test
//    void GetPlayerAdditionalInfoSuccessScenario() {
//        // Arrange
//        IPlayerAdditionalInfoRepository playerAdditionalInfoRepository = new PlayerAdditionalInfoStubDatabase();
//        IPlayerAdditionalInfoService playerAdditionalInfoService = new PlayerAdditionalInfoService(playerAdditionalInfoRepository);
//        IPlayerStatsRepository playersStatsRepository = new PlayerStatsStubDatabase();
//
//        // Act
//        PlayerAdditionalInfo playerAdditionalInfo = playerAdditionalInfoService.get(1l);
//
//        // Assert
//        Assertions.assertEquals(new PlayerAdditionalInfo(1l, 150, playersStatsRepository.get(0l)), playerAdditionalInfo);
//    }
//
//    @Test
//    void DeletePlayerAdditionalInfoSuccessScenario() {
//        // Arrange
//        IPlayerAdditionalInfoRepository playerAdditionalInfoRepository = new PlayerAdditionalInfoStubDatabase();
//        IPlayerAdditionalInfoService playerAdditionalInfoService = new PlayerAdditionalInfoService(playerAdditionalInfoRepository);
//        IPlayerStatsRepository playersStatsRepository = new PlayerStatsStubDatabase();
//
//        // Act
//        playerAdditionalInfoService.delete(1l);
//        List<PlayerAdditionalInfo> playersAdditionalInfo = playerAdditionalInfoService.getAll();
//
//        List<PlayerAdditionalInfo> playersAdditionalInfoExpected = new ArrayList<>();
//        playersAdditionalInfoExpected.add(new PlayerAdditionalInfo(1l, 150, playersStatsRepository.get(0l)));
//        playersAdditionalInfoExpected.add(new PlayerAdditionalInfo(2l, 120, playersStatsRepository.get(1l)));
//        playersAdditionalInfoExpected.remove(0);
//
//        // Assert
//        Assertions.assertEquals(playersAdditionalInfoExpected, playersAdditionalInfo);
//
//    }
//
//    @Test
//    void AddPlayerAdditionalInfoSuccessScenario() {
//        // Arrange
//        IPlayerAdditionalInfoRepository playerAdditionalInfoRepository = new PlayerAdditionalInfoStubDatabase();
//        IPlayerAdditionalInfoService playerAdditionalInfoService = new PlayerAdditionalInfoService(playerAdditionalInfoRepository);
//        IPlayerStatsRepository playersStatsRepository = new PlayerStatsStubDatabase();
//
//        // Act
//        playerAdditionalInfoService.add(new PlayerAdditionalInfo(3l, 180, playersStatsRepository.get(0l)));
//        List<PlayerAdditionalInfo> playersAdditionalInfo = playerAdditionalInfoService.getAll();
//
//        List<PlayerAdditionalInfo> playersAdditionalInfoExpected = new ArrayList<>();
//        playersAdditionalInfoExpected.add(new PlayerAdditionalInfo(1l, 150, playersStatsRepository.get(0l)));
//        playersAdditionalInfoExpected.add(new PlayerAdditionalInfo(2l, 120, playersStatsRepository.get(1l)));
//        playersAdditionalInfoExpected.add(new PlayerAdditionalInfo(3l, 180, playersStatsRepository.get(0l)));
//
//        // Assert
//        Assertions.assertEquals(playersAdditionalInfoExpected, playersAdditionalInfo);
//
//
//    }
//
//    @Test
//    void UpdatePlayerAdditionalInfoSuccessScenario(){
//        // Arrange
//        IPlayerAdditionalInfoRepository playerAdditionalInfoRepository = new PlayerAdditionalInfoStubDatabase();
//        IPlayerAdditionalInfoService playerAdditionalInfoService = new PlayerAdditionalInfoService(playerAdditionalInfoRepository);
//        IPlayerStatsRepository playersStatsRepository = new PlayerStatsStubDatabase();
//
//        // Act
//        playerAdditionalInfoService.update(new PlayerAdditionalInfo(2l, 180, playersStatsRepository.get(0l)));
//        List<PlayerAdditionalInfo> playersAdditionalInfo = playerAdditionalInfoService.getAll();
//
//        List<PlayerAdditionalInfo> playersAdditionalInfoExpected = new ArrayList<>();
//        playersAdditionalInfoExpected.add(new PlayerAdditionalInfo(1l, 150, playersStatsRepository.get(0l)));
//        playersAdditionalInfoExpected.add(new PlayerAdditionalInfo(2l, 120, playersStatsRepository.get(1l)));
//        playersAdditionalInfoExpected.set(1, new PlayerAdditionalInfo(2l, 180, playersStatsRepository.get(0l)));
//
//        // Assert
//        Assertions.assertEquals(playersAdditionalInfoExpected, playersAdditionalInfo);
//    }
//}
