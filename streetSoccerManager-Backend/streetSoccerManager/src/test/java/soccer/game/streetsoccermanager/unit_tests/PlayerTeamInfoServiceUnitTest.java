package soccer.game.streetsoccermanager.unit_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import soccer.game.streetsoccermanager.model.entities.*;
import soccer.game.streetsoccermanager.repository_interfaces.IPlayerTeamInfoRepository;
import soccer.game.streetsoccermanager.service.PlayerTeamInfoService;
import soccer.game.streetsoccermanager.service_interfaces.IPlayerTeamInfoService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
class PlayerTeamInfoServiceUnitTest {
    @Mock
    IPlayerTeamInfoRepository playerTeamInfoRepository;
    IPlayerTeamInfoService playerTeamInfoService;

    @BeforeEach
    public void setUp()  {
        List<PlayerTeamInfo> playersTeamInfo = List.of(
                new PlayerTeamInfo(1l, 10, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                new PlayerTeamInfo(2l, 1, new Team(1l, "Barcelona", new Formation(1l, "1-2-1")))
        );
        when(playerTeamInfoRepository.getAll()).thenReturn(playersTeamInfo);
        when(playerTeamInfoRepository.get(1l)).thenReturn(playersTeamInfo.get(0));
        when(playerTeamInfoRepository.get(2l)).thenReturn(playersTeamInfo.get(1));
        when(playerTeamInfoRepository.add(new PlayerTeamInfo(6, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))))).
                thenReturn(new PlayerTeamInfo(3l, 6, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))));
        when(playerTeamInfoRepository.update(new PlayerTeamInfo(2l, 48, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))))).
                thenReturn(new PlayerTeamInfo(2l, 48, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))));
        playerTeamInfoService = new PlayerTeamInfoService(playerTeamInfoRepository);
    }

    @Test
    void GetAllPlayersTeamInfoSuccessScenario() {
        // Act
        List<PlayerTeamInfo> playersTeamInfoActual = playerTeamInfoService.getAll();

        List<PlayerTeamInfo> playersTeamInfoExpected = new ArrayList<>();
        playersTeamInfoExpected.add(new PlayerTeamInfo(1l, 10, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))));
        playersTeamInfoExpected.add(new PlayerTeamInfo(2l, 1, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))));

        // Assert
        Assertions.assertEquals(playersTeamInfoExpected, playersTeamInfoActual);
    }

    @Test
    void GetPlayerTeamInfoSuccessScenario() {
        // Act
        PlayerTeamInfo playerTeamInfo = playerTeamInfoService.get(1l);

        // Assert
        Assertions.assertEquals(new PlayerTeamInfo(1l, 10, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))), playerTeamInfo);
    }

    @Test
    void DeletePlayerTeamInfoSuccessScenario(){
        // Act
        playerTeamInfoService.delete(1l);
        verify(playerTeamInfoRepository).delete(1l);
        when(playerTeamInfoRepository.delete(2l)).thenReturn(true);
        Assertions.assertEquals(true, playerTeamInfoService.delete(2l));
        when(playerTeamInfoRepository.get(3l)).thenReturn(null);
        when(playerTeamInfoRepository.delete(3l)).thenReturn(false);
        Assertions.assertEquals(false, playerTeamInfoService.delete(3l));
    }

    @Test
    void AddPlayerTeamInfoSuccessScenario() {
        // Act
        PlayerTeamInfo newPlayerTeamInfo = playerTeamInfoService.add(new PlayerTeamInfo(6, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))));
        // Assert
        Assertions.assertEquals(new PlayerTeamInfo(3l, 6, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))), newPlayerTeamInfo);
        Assertions.assertEquals(null, playerTeamInfoService.add(new PlayerTeamInfo(3l, 6, new Team(1l, "Barcelona", new Formation(1l, "1-2-1")))));
    }

    @Test
    void UpdatePlayerTeamInfoSuccessScenario(){
        // Act
        PlayerTeamInfo updatedPlayerTeamInfo = playerTeamInfoService.update(new PlayerTeamInfo(2l, 48, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))));
        // Assert
        Assertions.assertEquals(new PlayerTeamInfo(2l, 48, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))), updatedPlayerTeamInfo);
        Assertions.assertEquals(null, playerTeamInfoService.update(new PlayerTeamInfo(48, new Team(1l, "Barcelona", new Formation(1l, "1-2-1")))));
    }
}
