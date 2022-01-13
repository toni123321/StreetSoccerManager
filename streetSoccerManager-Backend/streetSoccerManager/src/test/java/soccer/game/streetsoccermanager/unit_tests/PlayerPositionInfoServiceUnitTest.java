package soccer.game.streetsoccermanager.unit_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import soccer.game.streetsoccermanager.model.entities.PlayerPositionInfo;
import soccer.game.streetsoccermanager.model.entities.Position;
import soccer.game.streetsoccermanager.repository_interfaces.IPlayerPositionInfoRepository;
import soccer.game.streetsoccermanager.service.PlayerPositionInfoService;
import soccer.game.streetsoccermanager.service_interfaces.IPlayerPositionInfoService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
class PlayerPositionInfoServiceUnitTest {
    @Mock
    IPlayerPositionInfoRepository playerPositionInfoRepository;
    IPlayerPositionInfoService playerPositionInfoService;

    @BeforeEach
    public void setUp()  {
        List<PlayerPositionInfo> playersPositionInfo = List.of(
                new PlayerPositionInfo(1l, new Position(1l, "ATACK", "ST"), new Position(1l,"ATACK", "ST"), true),
                new PlayerPositionInfo(2l, new Position(2l, "DEF", "CB"), new Position(2l,"DEF", "CB"), true)
        );
        when(playerPositionInfoRepository.getAll()).thenReturn(playersPositionInfo);
        when(playerPositionInfoRepository.get(1l)).thenReturn(playersPositionInfo.get(0));
        when(playerPositionInfoRepository.get(2l)).thenReturn(playersPositionInfo.get(1));
        when(playerPositionInfoRepository.add(
                new PlayerPositionInfo(new Position(3l, "ATACK", "LW"), new Position(3l,"ATACK", "LW"), false))).
                thenReturn(new PlayerPositionInfo(3l, new Position(3l, "ATACK", "LW"), new Position(3l,"ATACK", "LW"), false));
        when(playerPositionInfoRepository.update(
                new PlayerPositionInfo(2l, new Position(2l, "DEF", "CB"), new Position(2l,"DEF", "CB"), false))).
                thenReturn(new PlayerPositionInfo(2l, new Position(2l, "DEF", "CB"), new Position(2l,"DEF", "CB"), false));
        playerPositionInfoService = new PlayerPositionInfoService(playerPositionInfoRepository);
    }

    @Test
    void GetAllPlayersPositionInfoSuccessScenario() {
        // Act
        List<PlayerPositionInfo> playersPositionInfoService = playerPositionInfoService.getAll();

        List<PlayerPositionInfo> playersPositionInfoExpected = new ArrayList<>();
        playersPositionInfoExpected.add(new PlayerPositionInfo(1l, new Position(1l, "ATACK", "ST"), new Position(1l,"ATACK", "ST"), true));
        playersPositionInfoExpected.add(new PlayerPositionInfo(2l, new Position(2l, "DEF", "CB"), new Position(2l,"DEF", "CB"), true));

        // Assert
        Assertions.assertEquals(playersPositionInfoExpected, playersPositionInfoService);
    }

    @Test
    void GetPlayerPositionInfoSuccessScenario() {
        // Act
        PlayerPositionInfo playerPositionInfo = playerPositionInfoService.get(1l);

        // Assert
        Assertions.assertEquals(new PlayerPositionInfo(1l, new Position(1l, "ATACK", "ST"), new Position(1l,"ATACK", "ST"), true), playerPositionInfo);
    }

    @Test
    void DeletePlayerPositionInfoSuccessScenario(){
        // Act
        playerPositionInfoService.delete(1l);
        verify(playerPositionInfoRepository).delete(1l);
        when(playerPositionInfoRepository.delete(2l)).thenReturn(true);
        Assertions.assertEquals(true, playerPositionInfoService.delete(2l));
        when(playerPositionInfoRepository.get(3l)).thenReturn(null);
        when(playerPositionInfoRepository.delete(3l)).thenReturn(false);
        Assertions.assertEquals(false, playerPositionInfoService.delete(3l));
    }

    @Test
    void AddPlayerPositionInfoSuccessScenario() {
        // Act
        PlayerPositionInfo newPlayerPositionInfo = playerPositionInfoService.add(new PlayerPositionInfo(new Position(3l, "ATACK", "LW"), new Position(3l,"ATACK", "LW"), false));
        // Assert
        Assertions.assertEquals(new PlayerPositionInfo(3l, new Position(3l, "ATACK", "LW"), new Position(3l,"ATACK", "LW"), false), newPlayerPositionInfo);
        Assertions.assertEquals(null, playerPositionInfoService.add(new PlayerPositionInfo(3l, new Position(3l, "ATACK", "LW"), new Position(3l,"ATACK", "LW"), false)));
    }

    @Test
    void UpdatePlayerPositionInfoSuccessScenario(){
        // Act
        PlayerPositionInfo updatedPlayerPositionInfo = playerPositionInfoService.update(new PlayerPositionInfo(2l, new Position(2l, "DEF", "CB"), new Position(2l,"DEF", "CB"), false));
        // Assert
        Assertions.assertEquals(new PlayerPositionInfo(2l, new Position(2l, "DEF", "CB"), new Position(2l,"DEF", "CB"), false), updatedPlayerPositionInfo);
        Assertions.assertEquals(null, playerPositionInfoService.update(new PlayerPositionInfo(new Position(2l, "DEF", "CB"), new Position(2l,"DEF", "CB"), false)));
    }
}
