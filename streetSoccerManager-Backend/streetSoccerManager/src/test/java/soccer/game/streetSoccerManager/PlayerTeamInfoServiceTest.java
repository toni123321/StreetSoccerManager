package soccer.game.streetSoccerManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import soccer.game.streetSoccerManager.model.entities.PlayerPersonalInfo;
import soccer.game.streetSoccerManager.model.entities.PlayerTeamInfo;
import soccer.game.streetSoccerManager.repository.repositories.PlayerPersonalInfo.PlayerPersonalInfoFakeDatabase;
import soccer.game.streetSoccerManager.repository.repositories.PlayerTeamInfo.PlayerTeamInfoFakeDatabase;
import soccer.game.streetSoccerManager.repository.repositories.Team.TeamFakeDatabase;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPlayerPersonalInfoRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPlayerTeamInfoRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.ITeamRepository;
import soccer.game.streetSoccerManager.service.PlayerPersonalInfoService;
import soccer.game.streetSoccerManager.service.PlayerTeamInfoService;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IPlayerPersonalInfoService;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IPlayerTeamInfoService;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@SpringBootTest
public class PlayerTeamInfoServiceTest {
    @Test
    void GetAllPlayersTeamInfoSuccessScenario() {
        // Arrange
        IPlayerTeamInfoRepository playerTeamInfoRepository = new PlayerTeamInfoFakeDatabase();
        IPlayerTeamInfoService playerTeamInfoService = new PlayerTeamInfoService(playerTeamInfoRepository);
        ITeamRepository teamRepository = new TeamFakeDatabase();

        // Act
        List<PlayerTeamInfo> playersTeamInfo = playerTeamInfoService.getAll();

        List<PlayerTeamInfo> playersTeamInfoExpected = new ArrayList<>();
        playersTeamInfoExpected.add(new PlayerTeamInfo(1l, 1, teamRepository.get(0l)));
        playersTeamInfoExpected.add(new PlayerTeamInfo(2l, 2, teamRepository.get(0l)));


        // Assert
        Assertions.assertEquals(playersTeamInfoExpected, playersTeamInfo);

    }

    @Test
    void GetPlayerTeamInfoSuccessScenario() {
        // Arrange
        IPlayerTeamInfoRepository playerTeamInfoRepository = new PlayerTeamInfoFakeDatabase();
        IPlayerTeamInfoService playerTeamInfoService = new PlayerTeamInfoService(playerTeamInfoRepository);
        ITeamRepository teamRepository = new TeamFakeDatabase();

        // Act
        PlayerTeamInfo playerTeamInfo = playerTeamInfoService.get(1l);

        // Assert
        Assertions.assertEquals(new PlayerTeamInfo(1l, 1, teamRepository.get(0l)), playerTeamInfo);
    }

    @Test
    void DeletePlayerTeamInfoSuccessScenario() {
        // Arrange
        IPlayerTeamInfoRepository playerTeamInfoRepository = new PlayerTeamInfoFakeDatabase();
        IPlayerTeamInfoService playerTeamInfoService = new PlayerTeamInfoService(playerTeamInfoRepository);
        ITeamRepository teamRepository = new TeamFakeDatabase();

        // Act
        playerTeamInfoService.delete(1l);
        List<PlayerTeamInfo> playersTeamInfo = playerTeamInfoService.getAll();

        List<PlayerTeamInfo> playersTeamInfoExpected = new ArrayList<>();
        playersTeamInfoExpected.add(new PlayerTeamInfo(1l, 1, teamRepository.get(0l)));
        playersTeamInfoExpected.add(new PlayerTeamInfo(2l, 2, teamRepository.get(0l)));
        playersTeamInfoExpected.remove(0);

        // Assert
        Assertions.assertEquals(playersTeamInfoExpected, playersTeamInfo);
    }

    @Test
    void AddPlayerTeamInfoSuccessScenario() {
        // Arrange
        IPlayerTeamInfoRepository playerTeamInfoRepository = new PlayerTeamInfoFakeDatabase();
        IPlayerTeamInfoService playerTeamInfoService = new PlayerTeamInfoService(playerTeamInfoRepository);
        ITeamRepository teamRepository = new TeamFakeDatabase();

        // Act
        playerTeamInfoService.add(new PlayerTeamInfo(3l, 10, teamRepository.get(0l)));
        List<PlayerTeamInfo> playersTeamInfo = playerTeamInfoService.getAll();

        List<PlayerTeamInfo> playersTeamInfoExpected = new ArrayList<>();
        playersTeamInfoExpected.add(new PlayerTeamInfo(1l, 1, teamRepository.get(0l)));
        playersTeamInfoExpected.add(new PlayerTeamInfo(2l, 2, teamRepository.get(0l)));
        playersTeamInfoExpected.add(new PlayerTeamInfo(3l, 10, teamRepository.get(0l)));

        // Assert
        Assertions.assertEquals(playersTeamInfoExpected, playersTeamInfo);

    }

    @Test
    void UpdatePlayerTeamInfoSuccessScenario(){
        // Arrange
        IPlayerTeamInfoRepository playerTeamInfoRepository = new PlayerTeamInfoFakeDatabase();
        IPlayerTeamInfoService playerTeamInfoService = new PlayerTeamInfoService(playerTeamInfoRepository);
        ITeamRepository teamRepository = new TeamFakeDatabase();

        // Act
        playerTeamInfoService.update(new PlayerTeamInfo(2l, 10, teamRepository.get(0l)));
        List<PlayerTeamInfo> playersTeamInfo = playerTeamInfoService.getAll();

        List<PlayerTeamInfo> playersTeamInfoExpected = new ArrayList<>();
        playersTeamInfoExpected.add(new PlayerTeamInfo(1l, 1, teamRepository.get(0l)));
        playersTeamInfoExpected.add(new PlayerTeamInfo(2l, 2, teamRepository.get(0l)));
        playersTeamInfoExpected.set(1, new PlayerTeamInfo(2l, 10, teamRepository.get(0l)));

        // Assert
        Assertions.assertEquals(playersTeamInfoExpected, playersTeamInfo);

    }
}
