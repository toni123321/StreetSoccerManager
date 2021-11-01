package soccer.game.streetSoccerManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import soccer.game.streetSoccerManager.model.entities.PlayerPersonalInfo;
import soccer.game.streetSoccerManager.repository.repositories.PlayerPersonalInfo.PlayerPersonalInfoStubDatabase;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPlayerPersonalInfoRepository;
import soccer.game.streetSoccerManager.service.PlayerPersonalInfoService;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IPlayerPersonalInfoService;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@SpringBootTest
public class PlayerPersonalInfoServiceTest {
    @Test
    void GetAllPlayersPersonalInfoSuccessScenario() {
        // Arrange
        IPlayerPersonalInfoRepository playerPersonalInfoRepository = new PlayerPersonalInfoStubDatabase();
        IPlayerPersonalInfoService playerPersonalInfoService = new PlayerPersonalInfoService(playerPersonalInfoRepository);

        // Act
        List<PlayerPersonalInfo> playersPersonalInfo = playerPersonalInfoService.getAll();

        List<PlayerPersonalInfo> playersPersonalInfoExpected = new ArrayList<>();
        playersPersonalInfoExpected.add(new PlayerPersonalInfo(1l, "Javi","Diaz", new GregorianCalendar(1997, 5, 15)));
        playersPersonalInfoExpected.add(new PlayerPersonalInfo(2l, "Andres","Dumitrescu", new GregorianCalendar(2001, 3, 11)));


        // Assert
        Assertions.assertEquals(playersPersonalInfoExpected, playersPersonalInfo);

    }

    @Test
    void GetPlayerPersonalInfoSuccessScenario() {
        // Arrange
        IPlayerPersonalInfoRepository playerPersonalInfoRepository = new PlayerPersonalInfoStubDatabase();
        IPlayerPersonalInfoService playerPersonalInfoService = new PlayerPersonalInfoService(playerPersonalInfoRepository);

        // Act
        PlayerPersonalInfo playerPersonalInfo = playerPersonalInfoService.get(1l);
        // Assert
        Assertions.assertEquals(new PlayerPersonalInfo(1l, "Javi","Diaz", new GregorianCalendar(1997, 5, 15)), playerPersonalInfo);
    }

    @Test
    void DeletePlayerPersonalInfoSuccessScenario() {
        // Arrange
        IPlayerPersonalInfoRepository playerPersonalInfoRepository = new PlayerPersonalInfoStubDatabase();
        IPlayerPersonalInfoService playerPersonalInfoService = new PlayerPersonalInfoService(playerPersonalInfoRepository);

        // Act
        playerPersonalInfoService.delete(1l);
        List<PlayerPersonalInfo> playersPersonalInfo = playerPersonalInfoService.getAll();

        List<PlayerPersonalInfo> playersPersonalInfoExpected = new ArrayList<>();
        playersPersonalInfoExpected.add(new PlayerPersonalInfo(1l, "Javi","Diaz", new GregorianCalendar(1997, 5, 15)));
        playersPersonalInfoExpected.add(new PlayerPersonalInfo(2l, "Andres","Dumitrescu", new GregorianCalendar(2001, 3, 11)));
        playersPersonalInfoExpected.remove(0);

        // Assert
        Assertions.assertEquals(playersPersonalInfoExpected, playersPersonalInfo);
    }

    @Test
    void AddPlayerPersonalInfoSuccessScenario() {
        // Arrange
        IPlayerPersonalInfoRepository playerPersonalInfoRepository = new PlayerPersonalInfoStubDatabase();
        IPlayerPersonalInfoService playerPersonalInfoService = new PlayerPersonalInfoService(playerPersonalInfoRepository);

        // Act
        playerPersonalInfoService.add(new PlayerPersonalInfo(3l, "Lionel","Messi", new GregorianCalendar(1987, 6, 24)));
        List<PlayerPersonalInfo> playersPersonalInfo = playerPersonalInfoService.getAll();

        List<PlayerPersonalInfo> playersPersonalInfoExpected = new ArrayList<>();
        playersPersonalInfoExpected.add(new PlayerPersonalInfo(1l, "Javi","Diaz", new GregorianCalendar(1997, 5, 15)));
        playersPersonalInfoExpected.add(new PlayerPersonalInfo(2l, "Andres","Dumitrescu", new GregorianCalendar(2001, 3, 11)));
        playersPersonalInfoExpected.add(new PlayerPersonalInfo(3l, "Lionel","Messi", new GregorianCalendar(1987, 6, 24)));

        // Assert
        Assertions.assertEquals(playersPersonalInfoExpected, playersPersonalInfo);

    }

    @Test
    void UpdatePlayerPersonalInfoSuccessScenario(){
        // Arrange
        IPlayerPersonalInfoRepository playerPersonalInfoRepository = new PlayerPersonalInfoStubDatabase();
        IPlayerPersonalInfoService playerPersonalInfoService = new PlayerPersonalInfoService(playerPersonalInfoRepository);

        // Act
        playerPersonalInfoService.update(new PlayerPersonalInfo(1l, "Lionel","Messi", new GregorianCalendar(1987, 6, 24)));
        List<PlayerPersonalInfo> playersPersonalInfo = playerPersonalInfoService.getAll();

        List<PlayerPersonalInfo> playersPersonalInfoExpected = new ArrayList<>();
        playersPersonalInfoExpected.add(new PlayerPersonalInfo(1l, "Javi","Diaz", new GregorianCalendar(1997, 5, 15)));
        playersPersonalInfoExpected.add(new PlayerPersonalInfo(2l, "Andres","Dumitrescu", new GregorianCalendar(2001, 3, 11)));
        playersPersonalInfoExpected.set(0, new PlayerPersonalInfo(1l, "Lionel","Messi", new GregorianCalendar(1987, 6, 24)));

        // Assert
        Assertions.assertEquals(playersPersonalInfoExpected, playersPersonalInfo);

    }
}
