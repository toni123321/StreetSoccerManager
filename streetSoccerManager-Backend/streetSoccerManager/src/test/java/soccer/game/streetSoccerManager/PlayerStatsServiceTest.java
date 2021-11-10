//package soccer.game.streetSoccerManager;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import soccer.game.streetSoccerManager.model.entities.PlayerStats;
//import soccer.game.streetSoccerManager.repository.repositories.PlayerStats.PlayerStatsStubDatabase;
//import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPlayerStatsRepository;
//import soccer.game.streetSoccerManager.service.PlayerStatsService;
//import soccer.game.streetSoccerManager.serviceInterfaces.IPlayerStatsService;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@SpringBootTest
//public class PlayerStatsServiceTest {
//    @Test
//    void GetAllPLayersStatsSuccessScenario() {
//        // Arrange
//        IPlayerStatsRepository playerStatsRepository = new PlayerStatsStubDatabase();
//        IPlayerStatsService playerStatsService = new PlayerStatsService(playerStatsRepository);
//
//        // Act
//        List<PlayerStats> playerStats = playerStatsService.getAll();
//        playerStats = playerStats.stream().filter(stat -> stat.getId() < 2).collect(Collectors.toList());
//
//        List<PlayerStats> playerStatsExpected = new ArrayList<>();
//        playerStatsExpected.add(new PlayerStats(0l, 65));//package soccer.game.streetSoccerManager;
////
////import org.junit.jupiter.api.Assertions;
////import org.junit.jupiter.api.Test;
////import org.springframework.boot.test.context.SpringBootTest;
////import soccer.game.streetSoccerManager.model.entities.PlayerPersonalInfo;
////import soccer.game.streetSoccerManager.model.entities.PlayerPositionInfo;
////import soccer.game.streetSoccerManager.repository.repositories.PlayerPersonalInfo.PlayerPersonalInfoStubDatabase;
////import soccer.game.streetSoccerManager.repository.repositories.PlayerPositionInfo.PlayerPositionInfoStubDatabase;
////import soccer.game.streetSoccerManager.repository.repositories.Position.PositionStubDatabase;
////import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPlayerPersonalInfoRepository;
////import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPlayerPositionInfoRepository;
////import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPositionRepository;
////import soccer.game.streetSoccerManager.service.PlayerPersonalInfoService;
////import soccer.game.streetSoccerManager.service.PlayerPositionInfoService;
////import soccer.game.streetSoccerManager.serviceInterfaces.IPlayerPersonalInfoService;
////import soccer.game.streetSoccerManager.serviceInterfaces.IPlayerPositionInfoService;
////
////import java.util.ArrayList;
////import java.util.GregorianCalendar;
////import java.util.List;
////
////@SpringBootTest
////public class PlayerPositionInfoServiceTest {
////    @Test
////    void GetAllPlayersPositionInfoSuccessScenario() {
////        // Arrange
////        IPlayerPositionInfoRepository playerPositionInfoRepository = new PlayerPositionInfoStubDatabase();
////        IPlayerPositionInfoService playerPositionInfoService = new PlayerPositionInfoService(playerPositionInfoRepository);
////        IPositionRepository positionRepository = new PositionStubDatabase();
////        // Act
////        List<PlayerPositionInfo> playersPositionInfo = playerPositionInfoService.getAll().subList(0, 2);
////
////        List<PlayerPositionInfo> playersPositionInfoExpected = new ArrayList<>();
////        playersPositionInfoExpected.add(new PlayerPositionInfo(1l, 11, positionRepository.get(10l), positionRepository.get(10l), true));
////        playersPositionInfoExpected.add(new PlayerPositionInfo(2l, 8, positionRepository.get(8l), positionRepository.get(8l), true));
////
////
////        // Assert
////        Assertions.assertEquals(playersPositionInfoExpected, playersPositionInfo);
////
////    }
////
////    @Test
////    void GetPlayerPositionInfoSuccessScenario() {
////        // Arrange
////        IPlayerPositionInfoRepository playerPositionInfoRepository = new PlayerPositionInfoStubDatabase();
////        IPlayerPositionInfoService playerPositionInfoService = new PlayerPositionInfoService(playerPositionInfoRepository);
////        IPositionRepository positionRepository = new PositionStubDatabase();
////        // Act
////        PlayerPositionInfo playerPositionInfo = playerPositionInfoService.get(1l);
////
////        // Assert
////        Assertions.assertEquals(new PlayerPositionInfo(1l, 11, positionRepository.get(10l), positionRepository.get(10l), true), playerPositionInfo);
////    }
////
////    @Test
////    void DeletePlayerPositionInfoSuccessScenario() {
////        // Arrange
////        IPlayerPositionInfoRepository playerPositionInfoRepository = new PlayerPositionInfoStubDatabase();
////        IPlayerPositionInfoService playerPositionInfoService = new PlayerPositionInfoService(playerPositionInfoRepository);
////        IPositionRepository positionRepository = new PositionStubDatabase();
////        // Act
////        playerPositionInfoService.delete(1l);
////        List<PlayerPositionInfo> playersPositionInfo = playerPositionInfoService.getAll().subList(0, 1);
////
////        List<PlayerPositionInfo> playersPositionInfoExpected = new ArrayList<>();
////        playersPositionInfoExpected.add(new PlayerPositionInfo(1l, 11, positionRepository.get(10l), positionRepository.get(10l), true));
////        playersPositionInfoExpected.add(new PlayerPositionInfo(2l, 8, positionRepository.get(8l), positionRepository.get(8l), true));
////        playersPositionInfoExpected.remove(0);
////
////        // Assert
////        Assertions.assertEquals(playersPositionInfoExpected, playersPositionInfo);
////    }
////
////    @Test
////    void AddPlayerPositionInfoSuccessScenario() {
////        // Arrange
////        IPlayerPositionInfoRepository playerPositionInfoRepository = new PlayerPositionInfoStubDatabase();
////        IPlayerPositionInfoService playerPositionInfoService = new PlayerPositionInfoService(playerPositionInfoRepository);
////        IPositionRepository positionRepository = new PositionStubDatabase();
////        // Act
////        playerPositionInfoService.add(new PlayerPositionInfo(4l, 10, positionRepository.get(4l), positionRepository.get(8l), true));
////        List<PlayerPositionInfo> playersPositionInfo = playerPositionInfoService.getAll().subList(playerPositionInfoService.getAll().size()-2, playerPositionInfoService.getAll().size());
////        //playersPositionInfo.add(playerPositionInfoService.getAll().get(playerPositionInfoService.getAll().size() - 1));
////
////        List<PlayerPositionInfo> playersPositionInfoExpected = new ArrayList<>();
////        playersPositionInfoExpected.add(new PlayerPositionInfo(3l, 17, positionRepository.get(2l), positionRepository.get(15l), false));
////        playersPositionInfoExpected.add(new PlayerPositionInfo(4l, 10, positionRepository.get(4l), positionRepository.get(8l), true));
////
////
////        // Assert
////        Assertions.assertEquals(playersPositionInfoExpected, playersPositionInfo);
////    }
////
////    @Test
////    void UpdatePlayerPositionInfoSuccessScenario(){
////        // Arrange
////        IPlayerPositionInfoRepository playerPositionInfoRepository = new PlayerPositionInfoStubDatabase();
////        IPlayerPositionInfoService playerPositionInfoService = new PlayerPositionInfoService(playerPositionInfoRepository);
////        IPositionRepository positionRepository = new PositionStubDatabase();
////        // Act
////        playerPositionInfoService.update(new PlayerPositionInfo(1l, 1, positionRepository.get(10l), positionRepository.get(10l), true));
////        List<PlayerPositionInfo> playersPositionInfo = playerPositionInfoService.getAll().subList(0, 2);
////
////        List<PlayerPositionInfo> playersPositionInfoExpected = new ArrayList<>();
////        playersPositionInfoExpected.add(new PlayerPositionInfo(1l, 11, positionRepository.get(10l), positionRepository.get(10l), true));
////        playersPositionInfoExpected.add(new PlayerPositionInfo(2l, 8, positionRepository.get(8l), positionRepository.get(8l), true));
////        playersPositionInfoExpected.set(0, new PlayerPositionInfo(1l, 1, positionRepository.get(10l), positionRepository.get(10l), true));
////
////        // Assert
////        Assertions.assertEquals(playersPositionInfoExpected, playersPositionInfo);
////
////    }
////}
//
//        playerStatsExpected.add(new PlayerStats(1l, 60));
//
//        // Assert
//        Assertions.assertEquals(playerStatsExpected, playerStats);
//
//    }
//
//    @Test
//    void GetPlayerStatsSuccessScenario() {
//        // Arrange
//        IPlayerStatsRepository playerStatsRepository = new PlayerStatsStubDatabase();
//        IPlayerStatsService playerStatsService = new PlayerStatsService(playerStatsRepository);
//
//        // Act
//        PlayerStats playerStats = playerStatsService.get(0l);
//        // Assert
//        Assertions.assertEquals(new PlayerStats(0l, 65), playerStats);
//    }
//
//    @Test
//    void DeletePlayerStatsSuccessScenario(){
//        // Arrange
//        IPlayerStatsRepository playerStatsRepository = new PlayerStatsStubDatabase();
//        IPlayerStatsService playerStatsService = new PlayerStatsService(playerStatsRepository);
//
//        // Act
//        playerStatsService.delete(0l);
//        List<PlayerStats> playerStats = playerStatsService.getAll();
//        playerStats = playerStats.stream().filter(stat -> stat.getId() < 2).collect(Collectors.toList());
//
//        List<PlayerStats> playerStatsExpected = new ArrayList<>();
//        playerStatsExpected.add(new PlayerStats(0l, 65));
//        playerStatsExpected.add(new PlayerStats(1l, 60));
//        playerStatsExpected.remove(0);
//
//        // Assert
//        Assertions.assertEquals(playerStatsExpected, playerStats);
//    }
//
//    @Test
//    void AddPlayerStatsSuccessScenario() {
//        // Arrange
//        IPlayerStatsRepository playerStatsRepository = new PlayerStatsStubDatabase();
//        IPlayerStatsService playerStatsService = new PlayerStatsService(playerStatsRepository);
//
//        // Act
//        playerStatsService.add(new PlayerStats(10l, 84));
//        List<PlayerStats> playerStats = playerStatsService.getAll();
//        playerStats = playerStats.stream().filter(stat -> stat.getId() < 2 || stat.getId().equals(10l)).collect(Collectors.toList());
//
//        List<PlayerStats> playerStatsExpected = new ArrayList<>();
//        playerStatsExpected.add(new PlayerStats(0l, 65));
//        playerStatsExpected.add(new PlayerStats(1l, 60));
//        playerStatsExpected.add(new PlayerStats(10l, 84));
//
//
//        // Assert
//        Assertions.assertEquals(playerStatsExpected, playerStats);
//    }
//
//    @Test
//    void UpdatePlayerStatsSuccessScenario(){
//        // Arrange
//        IPlayerStatsRepository playerStatsRepository = new PlayerStatsStubDatabase();
//        IPlayerStatsService playerStatsService = new PlayerStatsService(playerStatsRepository);
//
//        // Act
//        playerStatsService.update(new PlayerStats(0l, 84));
//        List<PlayerStats> playerStats = playerStatsService.getAll();
//        playerStats = playerStats.stream().filter(stat -> stat.getId() < 2).collect(Collectors.toList());
//
//        List<PlayerStats> playerStatsExpected = new ArrayList<>();
//        playerStatsExpected.add(new PlayerStats(0l, 65));
//        playerStatsExpected.add(new PlayerStats(1l, 60));
//        playerStatsExpected.set(0, new PlayerStats(0l, 84));
//
//        // Assert
//        Assertions.assertEquals(playerStatsExpected, playerStats);
//    }
//}
