package soccer.game.streetsocceranager.integration_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import soccer.game.streetsocceranager.model.entities.*;
import soccer.game.streetsocceranager.service.*;

import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
class PlayerPositionInfoIntegrationTest {
    //Arrange
    @Autowired
    PlayerPositionInfoService playerPositionInfoService;
    List<PlayerPositionInfo> playersPositionInfo = new ArrayList<>();
    List<PlayerPositionInfo> playersPositionInfoExpected = new ArrayList<>();
    @Autowired
    PositionService positionService;

    @BeforeEach
    void clearDB() {
        // Clear
        playerPositionInfoService.deleteAll();
        positionService.deleteAll();

        playersPositionInfoExpected.clear();

        // Add
        positionService.add(new Position("ATACK", "ST"));
        positionService.add(new Position("ATACK", "LW"));

        playerPositionInfoService.add(new PlayerPositionInfo(positionService.getAll().get(0), positionService.getAll().get(0), true));
        playerPositionInfoService.add(new PlayerPositionInfo(positionService.getAll().get(1), positionService.getAll().get(1), false));
        playersPositionInfo = playerPositionInfoService.getAll();
        playersPositionInfoExpected.add(new PlayerPositionInfo(playersPositionInfo.get(0).getId(), positionService.getAll().get(0), positionService.getAll().get(0), true));
        playersPositionInfoExpected.add(new PlayerPositionInfo(playersPositionInfo.get(1).getId(),  positionService.getAll().get(1), positionService.getAll().get(1), false));
    }

    @Test
    void getAllPlayersPositionInfoSuccessScenario() {
        // Assert
        Assertions.assertEquals(playersPositionInfoExpected, playerPositionInfoService.getAll()) ;
    }


    @Test
    void GetPlayerPositionInfoSuccessScenario() {
        // Act
        PlayerPositionInfo playerPositionInfo = playerPositionInfoService.get(playersPositionInfo.get(0).getId());

        // Assert
        Assertions.assertEquals(new PlayerPositionInfo(playersPositionInfo.get(0).getId(), positionService.getAll().get(0), positionService.getAll().get(0), true), playerPositionInfo);
    }

    @Test
    void DeletePlayerPositionInfoSuccessScenario(){
        // Act
        playerPositionInfoService.delete(playersPositionInfo.get(0).getId());
        playersPositionInfoExpected.remove(0);

        // Assert
        Assertions.assertEquals(playersPositionInfoExpected, playerPositionInfoService.getAll());
    }

    @Test
    void AddPlayerPositionInfoSuccessScenario() {
        // Act
        playerPositionInfoService.add(new PlayerPositionInfo(positionService.getAll().get(0), positionService.getAll().get(1), true));
        playersPositionInfo = playerPositionInfoService.getAll();
        Long lastIndexId = playersPositionInfo.get(playersPositionInfo.size() - 1).getId();
        playersPositionInfoExpected.add(new PlayerPositionInfo(lastIndexId, positionService.getAll().get(0), positionService.getAll().get(1), true));

        // Assert
        Assertions.assertEquals(playersPositionInfoExpected, playerPositionInfoService.getAll());
    }

    @Test
    void UpdatePlayerPositionInfoSuccessScenario(){
        // Act
        playerPositionInfoService.update(new PlayerPositionInfo(playersPositionInfo.get(0).getId(), positionService.getAll().get(0), positionService.getAll().get(0), false));
        playersPositionInfoExpected.set(0, new PlayerPositionInfo(playersPositionInfo.get(0).getId(), positionService.getAll().get(0), positionService.getAll().get(0), false));

        // Assert
        Assertions.assertEquals(playersPositionInfoExpected, playerPositionInfoService.getAll());
    }
}
