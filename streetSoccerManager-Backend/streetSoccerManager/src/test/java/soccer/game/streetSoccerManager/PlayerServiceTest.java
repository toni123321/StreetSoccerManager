package soccer.game.streetSoccerManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import soccer.game.streetSoccerManager.model.entities.Player;
import soccer.game.streetSoccerManager.model.entities.PlayerPersonalInfo;
import soccer.game.streetSoccerManager.repository.repositories.Player.PlayerFakeDatabase;
import soccer.game.streetSoccerManager.repository.repositories.PlayerAdditionalInfo.PlayerAdditionalInfoFakeDatabase;
import soccer.game.streetSoccerManager.repository.repositories.PlayerPersonalInfo.PlayerPersonalInfoFakeDatabase;
import soccer.game.streetSoccerManager.repository.repositories.PlayerPositionInfo.PlayerPositionInfoDatabase;
import soccer.game.streetSoccerManager.repository.repositories.PlayerPositionInfo.PlayerPositionInfoFakeDatabase;
import soccer.game.streetSoccerManager.repository.repositories.PlayerTeamInfo.PlayerTeamInfoFakeDatabase;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.*;
import soccer.game.streetSoccerManager.service.PlayerPersonalInfoService;
import soccer.game.streetSoccerManager.service.PlayerService;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IPlayerPersonalInfoService;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IPlayerService;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@SpringBootTest
public class PlayerServiceTest {
    @Test
    void GetAllPlayersSuccessScenario() {
        // Arrange
        IPlayerRepository playerRepository = new PlayerFakeDatabase();
        IPlayerService playerService = new PlayerService(playerRepository);

        IPlayerPersonalInfoRepository playerPersonalInfoRepository = new PlayerPersonalInfoFakeDatabase();
        IPlayerPositionInfoRepository playerPositionInfoRepository = new PlayerPositionInfoFakeDatabase();
        IPlayerTeamInfoRepository playerTeamInfoRepository = new PlayerTeamInfoFakeDatabase();
        IPlayerAdditionalInfoRepository playerAdditionalInfoRepository = new PlayerAdditionalInfoFakeDatabase();

        // Act
        List<Player> players = playerService.getAll();

        List<Player> playersExpected = new ArrayList<>();
        playersExpected.add(new Player(1l, playerPersonalInfoRepository.get(1l), playerPositionInfoRepository.get(1l), playerTeamInfoRepository.get(1l), playerAdditionalInfoRepository.get(1l)));
        playersExpected.add(new Player(2l, playerPersonalInfoRepository.get(2l), playerPositionInfoRepository.get(2l), playerTeamInfoRepository.get(2l), playerAdditionalInfoRepository.get(2l)));

        // Assert
        Assertions.assertEquals(playersExpected, players);

    }

    @Test
    void GetPlayerSuccessScenario() {
        // Arrange
        IPlayerRepository playerRepository = new PlayerFakeDatabase();
        IPlayerService playerService = new PlayerService(playerRepository);

        IPlayerPersonalInfoRepository playerPersonalInfoRepository = new PlayerPersonalInfoFakeDatabase();
        IPlayerPositionInfoRepository playerPositionInfoRepository = new PlayerPositionInfoFakeDatabase();
        IPlayerTeamInfoRepository playerTeamInfoRepository = new PlayerTeamInfoFakeDatabase();
        IPlayerAdditionalInfoRepository playerAdditionalInfoRepository = new PlayerAdditionalInfoFakeDatabase();

        // Act
        Player player = playerService.get(1l);

        // Assert
        Assertions.assertEquals(new Player(1l, playerPersonalInfoRepository.get(1l), playerPositionInfoRepository.get(1l), playerTeamInfoRepository.get(1l), playerAdditionalInfoRepository.get(1l)), player);
    }

    @Test
    void DeletePlayerSuccessScenario() {
        IPlayerRepository playerRepository = new PlayerFakeDatabase();
        IPlayerService playerService = new PlayerService(playerRepository);

        IPlayerPersonalInfoRepository playerPersonalInfoRepository = new PlayerPersonalInfoFakeDatabase();
        IPlayerPositionInfoRepository playerPositionInfoRepository = new PlayerPositionInfoFakeDatabase();
        IPlayerTeamInfoRepository playerTeamInfoRepository = new PlayerTeamInfoFakeDatabase();
        IPlayerAdditionalInfoRepository playerAdditionalInfoRepository = new PlayerAdditionalInfoFakeDatabase();

        // Act
        playerService.delete(1l);
        List<Player> players = playerService.getAll();

        List<Player> playersExpected = new ArrayList<>();
        playersExpected.add(new Player(1l, playerPersonalInfoRepository.get(1l), playerPositionInfoRepository.get(1l), playerTeamInfoRepository.get(1l), playerAdditionalInfoRepository.get(1l)));
        playersExpected.add(new Player(2l, playerPersonalInfoRepository.get(2l), playerPositionInfoRepository.get(2l), playerTeamInfoRepository.get(2l), playerAdditionalInfoRepository.get(2l)));
        playersExpected.remove(0);

        // Assert
        Assertions.assertEquals(playersExpected, players);
    }

    @Test
    void AddPlayerSuccessScenario() {
        IPlayerRepository playerRepository = new PlayerFakeDatabase();
        IPlayerService playerService = new PlayerService(playerRepository);

        IPlayerPersonalInfoRepository playerPersonalInfoRepository = new PlayerPersonalInfoFakeDatabase();
        IPlayerPositionInfoRepository playerPositionInfoRepository = new PlayerPositionInfoFakeDatabase();
        IPlayerTeamInfoRepository playerTeamInfoRepository = new PlayerTeamInfoFakeDatabase();
        IPlayerAdditionalInfoRepository playerAdditionalInfoRepository = new PlayerAdditionalInfoFakeDatabase();

        // Act
        playerService.add(new Player(3l, playerPersonalInfoRepository.get(1l), playerPositionInfoRepository.get(2l), playerTeamInfoRepository.get(1l), playerAdditionalInfoRepository.get(1l)));
        List<Player> players = playerService.getAll();

        List<Player> playersExpected = new ArrayList<>();
        playersExpected.add(new Player(1l, playerPersonalInfoRepository.get(1l), playerPositionInfoRepository.get(1l), playerTeamInfoRepository.get(1l), playerAdditionalInfoRepository.get(1l)));
        playersExpected.add(new Player(2l, playerPersonalInfoRepository.get(2l), playerPositionInfoRepository.get(2l), playerTeamInfoRepository.get(2l), playerAdditionalInfoRepository.get(2l)));
        playersExpected.add(new Player(3l, playerPersonalInfoRepository.get(1l), playerPositionInfoRepository.get(2l), playerTeamInfoRepository.get(1l), playerAdditionalInfoRepository.get(1l)));

        // Assert
        Assertions.assertEquals(playersExpected, players);
    }

    @Test
    void UpdatePlayerSuccessScenario(){
        IPlayerRepository playerRepository = new PlayerFakeDatabase();
        IPlayerService playerService = new PlayerService(playerRepository);

        IPlayerPersonalInfoRepository playerPersonalInfoRepository = new PlayerPersonalInfoFakeDatabase();
        IPlayerPositionInfoRepository playerPositionInfoRepository = new PlayerPositionInfoFakeDatabase();
        IPlayerTeamInfoRepository playerTeamInfoRepository = new PlayerTeamInfoFakeDatabase();
        IPlayerAdditionalInfoRepository playerAdditionalInfoRepository = new PlayerAdditionalInfoFakeDatabase();

        // Act
        playerService.update(new Player(2l, playerPersonalInfoRepository.get(1l), playerPositionInfoRepository.get(2l), playerTeamInfoRepository.get(1l), playerAdditionalInfoRepository.get(1l)));
        List<Player> players = playerService.getAll();

        List<Player> playersExpected = new ArrayList<>();
        playersExpected.add(new Player(1l, playerPersonalInfoRepository.get(1l), playerPositionInfoRepository.get(1l), playerTeamInfoRepository.get(1l), playerAdditionalInfoRepository.get(1l)));
        playersExpected.add(new Player(2l, playerPersonalInfoRepository.get(2l), playerPositionInfoRepository.get(2l), playerTeamInfoRepository.get(2l), playerAdditionalInfoRepository.get(2l)));
        playersExpected.set(1, new Player(2l, playerPersonalInfoRepository.get(1l), playerPositionInfoRepository.get(2l), playerTeamInfoRepository.get(1l), playerAdditionalInfoRepository.get(1l)));

        // Assert
        Assertions.assertEquals(playersExpected, players);

    }
}
