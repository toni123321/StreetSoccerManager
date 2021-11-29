package soccer.game.streetsoccermanager.unit_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import soccer.game.streetsoccermanager.model.entities.PlayerPersonalInfo;
import soccer.game.streetsoccermanager.repository_interfaces.IPlayerPersonalInfoRepository;
import soccer.game.streetsoccermanager.service.PlayerPersonalInfoService;
import soccer.game.streetsoccermanager.service_interfaces.IPlayerPersonalInfoService;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
class PlayerPersonalInfoUnitTest {
    @Mock
    IPlayerPersonalInfoRepository playerPersonalInfoRepository;
    IPlayerPersonalInfoService playerPersonalInfoService;

    @BeforeEach
    public void setUp()  {
        List<PlayerPersonalInfo> playersPersonalInfo = List.of(
                new PlayerPersonalInfo(1l,"Lionel", "Messi", new GregorianCalendar(1997, 5, 15)),
                new PlayerPersonalInfo(2l,"Juan", "Mata", new GregorianCalendar(1985, 5, 15))
        );
        when(playerPersonalInfoRepository.getAll()).thenReturn(playersPersonalInfo);
        when(playerPersonalInfoRepository.get(1l)).thenReturn(playersPersonalInfo.get(0));
        when(playerPersonalInfoRepository.get(2l)).thenReturn(playersPersonalInfo.get(1));
        when(playerPersonalInfoRepository.add(new PlayerPersonalInfo("Luis", "Suarez", new GregorianCalendar(1987, 5, 15)))).thenReturn(new PlayerPersonalInfo(3l, "Luis", "Suarez", new GregorianCalendar(1987, 5, 15)));
        when(playerPersonalInfoRepository.update(new PlayerPersonalInfo(2l, "Juan", "Mata", new GregorianCalendar(1985, 5, 25)))).thenReturn(new PlayerPersonalInfo(2l, "Juan", "Mata", new GregorianCalendar(1985, 5, 25)));
        playerPersonalInfoService = new PlayerPersonalInfoService(playerPersonalInfoRepository);
    }

    @Test
    void GetAllPlayersPersonalInfoSuccessScenario() {
        // Act
        List<PlayerPersonalInfo> playersPersonalInfo = playerPersonalInfoService.getAll();

        List<PlayerPersonalInfo> playersPersonalInfoExpected = new ArrayList<>();
        playersPersonalInfoExpected.add(new PlayerPersonalInfo(1l, "Lionel", "Messi", new GregorianCalendar(1997, 5, 15)));
        playersPersonalInfoExpected.add(new PlayerPersonalInfo(2l, "Juan", "Mata", new GregorianCalendar(1985, 5, 15)));

        // Assert
        Assertions.assertEquals(playersPersonalInfoExpected, playersPersonalInfo);
    }

    @Test
    void GetPlayerPersonalInfoSuccessScenario() {
        // Act
        PlayerPersonalInfo playerPersonalInfo = playerPersonalInfoService.get(1l);

        // Assert
        Assertions.assertEquals(new PlayerPersonalInfo(1l,"Lionel", "Messi", new GregorianCalendar(1997, 5, 15)), playerPersonalInfo);
    }

    @Test
    void DeletePlayerPersonalInfoSuccessScenario(){
        // Act
        playerPersonalInfoService.delete(1l);
        verify(playerPersonalInfoRepository).delete(1l);
        when(playerPersonalInfoRepository.delete(2l)).thenReturn(true);
        Assertions.assertEquals(true, playerPersonalInfoService.delete(2l));
        when(playerPersonalInfoRepository.get(3l)).thenReturn(null);
        when(playerPersonalInfoRepository.delete(3l)).thenReturn(false);
        Assertions.assertEquals(false, playerPersonalInfoService.delete(3l));
    }

    @Test
    void AddPlayerPersonalInfoSuccessScenario() {
        // Act
        PlayerPersonalInfo newPlayerPersonalInfo = playerPersonalInfoService.add(new PlayerPersonalInfo("Luis", "Suarez", new GregorianCalendar(1987, 5, 15)));
        // Assert
        Assertions.assertEquals(new PlayerPersonalInfo(3l, "Luis", "Suarez", new GregorianCalendar(1987, 5, 15)), newPlayerPersonalInfo);
        Assertions.assertEquals(null, playerPersonalInfoService.add(new PlayerPersonalInfo(3l,"Luis", "Suarez", new GregorianCalendar(1987, 5, 15))));
    }

    @Test
    void UpdatePlayerPersonalInfoSuccessScenario(){
        // Act
        PlayerPersonalInfo updatedPlayerPersonalInfo = playerPersonalInfoService.update(new PlayerPersonalInfo(2l, "Juan", "Mata", new GregorianCalendar(1985, 5, 25)));
        // Assert
        Assertions.assertEquals(new PlayerPersonalInfo(2l, "Juan", "Mata", new GregorianCalendar(1985, 5, 25)), updatedPlayerPersonalInfo);
        Assertions.assertEquals(null, playerPersonalInfoService.update(new PlayerPersonalInfo("Juan", "Mata", new GregorianCalendar(1985, 5, 25))));
    }
}
