package soccer.game.streetsoccermanager.unit_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import soccer.game.streetsoccermanager.model.entities.*;
import soccer.game.streetsoccermanager.repository_interfaces.IPlayerAdditionalInfoRepository;
import soccer.game.streetsoccermanager.service.PlayerAdditionalInfoService;
import soccer.game.streetsoccermanager.service_interfaces.IPlayerAdditionalInfoService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
class PlayerAdditionalInfoServiceUnitTest {
    @Mock
    IPlayerAdditionalInfoRepository playerAdditionalInfoRepository;
    IPlayerAdditionalInfoService playerAdditionalInfoService;

    @BeforeEach
    public void setUp()  {
        List<PlayerAdditionalInfo> playersAdditionalInfo = List.of(
                new PlayerAdditionalInfo(1l, 150, new PlayerStats(1l, 70, 80)),
                new PlayerAdditionalInfo(2l, 120, new PlayerStats(2l, 82, 76))
        );
        when(playerAdditionalInfoRepository.getAll()).thenReturn(playersAdditionalInfo);
        when(playerAdditionalInfoRepository.get(1l)).thenReturn(playersAdditionalInfo.get(0));
        when(playerAdditionalInfoRepository.get(2l)).thenReturn(playersAdditionalInfo.get(1));
        when(playerAdditionalInfoRepository.add(new PlayerAdditionalInfo(200, new PlayerStats(3l, 79, 84)))).
                thenReturn(new PlayerAdditionalInfo(3l, 200, new PlayerStats(1l, 79, 84)));
        when(playerAdditionalInfoRepository.update(new PlayerAdditionalInfo(2l, 250, new PlayerStats(2l, 82, 76)))).
                thenReturn(new PlayerAdditionalInfo(2l, 250, new PlayerStats(2l, 82, 76)));
        playerAdditionalInfoService = new PlayerAdditionalInfoService(playerAdditionalInfoRepository);
    }

    @Test
    void GetAllPlayersAdditionalInfoSuccessScenario() {
        // Act
        List<PlayerAdditionalInfo> playersAdditionalInfoActual = playerAdditionalInfoService.getAll();

        List<PlayerAdditionalInfo> playersAdditionalInfoExpected = new ArrayList<>();
        playersAdditionalInfoExpected.add(new PlayerAdditionalInfo(1l, 150, new PlayerStats(1l, 70, 80)));
        playersAdditionalInfoExpected.add(new PlayerAdditionalInfo(2l, 120, new PlayerStats(2l, 82, 76)));

        // Assert
        Assertions.assertEquals(playersAdditionalInfoExpected, playersAdditionalInfoActual);
    }

    @Test
    void GetPlayerAdditionalInfoSuccessScenario() {
        // Act
        PlayerAdditionalInfo playerAdditionalInfo = playerAdditionalInfoService.get(1l);

        // Assert
        Assertions.assertEquals(new PlayerAdditionalInfo(1l, 150, new PlayerStats(1l, 70, 80)), playerAdditionalInfo);
    }

    @Test
    void DeletePlayerAdditionalInfoSuccessScenario(){
        // Act
        playerAdditionalInfoService.delete(1l);
        verify(playerAdditionalInfoRepository).delete(1l);
        when(playerAdditionalInfoRepository.delete(2l)).thenReturn(true);
        Assertions.assertEquals(true, playerAdditionalInfoService.delete(2l));
        when(playerAdditionalInfoRepository.get(3l)).thenReturn(null);
        when(playerAdditionalInfoRepository.delete(3l)).thenReturn(false);
        Assertions.assertEquals(false, playerAdditionalInfoService.delete(3l));
    }

    @Test
    void AddPlayerAdditionalInfoSuccessScenario() {
        // Act
        PlayerAdditionalInfo newPlayerAdditionalInfo = playerAdditionalInfoService.add(new PlayerAdditionalInfo(200, new PlayerStats(3l, 79, 84)));
        // Assert
        Assertions.assertEquals(new PlayerAdditionalInfo(3l, 200, new PlayerStats(3l, 79, 84)), newPlayerAdditionalInfo);
        Assertions.assertEquals(null, playerAdditionalInfoService.add(new PlayerAdditionalInfo(3l, 200, new PlayerStats(3l, 79, 84))));
    }

    @Test
    void UpdatePlayerAdditionalInfoSuccessScenario(){
        // Act
        PlayerAdditionalInfo updatedPlayerAdditionalInfo = playerAdditionalInfoService.update(new PlayerAdditionalInfo(2l, 250, new PlayerStats(2l, 82, 76)));
        // Assert
        Assertions.assertEquals(new PlayerAdditionalInfo(2l, 250, new PlayerStats(2l, 82, 76)), updatedPlayerAdditionalInfo);
        Assertions.assertEquals(null, playerAdditionalInfoService.update(new PlayerAdditionalInfo( 250, new PlayerStats(2l, 82, 76))));
    }
}
