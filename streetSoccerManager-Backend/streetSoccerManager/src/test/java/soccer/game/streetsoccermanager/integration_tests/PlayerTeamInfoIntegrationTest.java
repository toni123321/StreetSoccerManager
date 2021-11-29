package soccer.game.streetsoccermanager.integration_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import soccer.game.streetsoccermanager.model.entities.*;
import soccer.game.streetsoccermanager.service.FormationService;
import soccer.game.streetsoccermanager.service.PlayerTeamInfoService;
import soccer.game.streetsoccermanager.service.TeamService;
import soccer.game.streetsoccermanager.service.UserService;

import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
class PlayerTeamInfoIntegrationTest {

    //Arrange
    @Autowired
    PlayerTeamInfoService playersTeamInfoService;
    List<PlayerTeamInfo> playersTeamInfo = new ArrayList<>();
    List<PlayerTeamInfo> playersTeamInfoExpected = new ArrayList<>();
    @Autowired
    TeamService teamService;
    @Autowired
    UserService userService;
    @Autowired
    FormationService formationService;

    @BeforeEach
    void clearDB() {
        // Clear
        playersTeamInfoService.deleteAll();
        userService.deleteAll();
        teamService.deleteAll();
        formationService.deleteAll();

        playersTeamInfoExpected.clear();

        // Add
        userService.add(new UserEntity("peter@gmail.com", "123", "Peter", "Petrov", "pesho"));
        formationService.add(new Formation("1-2-1"));
        teamService.add(new CustomTeam("Barca 2020", formationService.getAll().get(0), userService.getAll().get(0)));

        playersTeamInfoService.add(new PlayerTeamInfo(2, teamService.getAll().get(0)));
        playersTeamInfoService.add(new PlayerTeamInfo(10, teamService.getAll().get(0)));
        playersTeamInfo = playersTeamInfoService.getAll();
        playersTeamInfoExpected.add(new PlayerTeamInfo(playersTeamInfo.get(0).getId(), 2, teamService.getAll().get(0)));
        playersTeamInfoExpected.add(new PlayerTeamInfo(playersTeamInfo.get(1).getId(),  10, teamService.getAll().get(0)));
    }

    @Test
    void getAllPlayersTeamInfoSuccessScenario() {
        // Assert
        Assertions.assertEquals(playersTeamInfoExpected, playersTeamInfoService.getAll()) ;
    }


    @Test
    void GetPlayerTeamInfoSuccessScenario() {
        // Act
        PlayerTeamInfo playerTeamInfo = playersTeamInfoService.get(playersTeamInfo.get(0).getId());

        // Assert
        Assertions.assertEquals(new PlayerTeamInfo(playersTeamInfo.get(0).getId(), 2, teamService.getAll().get(0)), playerTeamInfo);
    }

    @Test
    void DeletePlayerTeamInfoSuccessScenario(){
        // Act
        playersTeamInfoService.delete(playersTeamInfo.get(0).getId());
        playersTeamInfoExpected.remove(0);

        // Assert
        Assertions.assertEquals(playersTeamInfoExpected, playersTeamInfoService.getAll());
    }

    @Test
    void AddPlayerTeamInfoSuccessScenario() {
        // Act
        playersTeamInfoService.add(new PlayerTeamInfo(12, teamService.getAll().get(0)));
        playersTeamInfo = playersTeamInfoService.getAll();
        Long lastIndexId = playersTeamInfo.get(playersTeamInfo.size() - 1).getId();
        playersTeamInfoExpected.add(new PlayerTeamInfo(lastIndexId, 12, teamService.getAll().get(0)));

        // Assert
        Assertions.assertEquals(playersTeamInfoExpected, playersTeamInfoService.getAll());
    }

    @Test
    void UpdatePlayerTeamInfoSuccessScenario(){
        // Act
        playersTeamInfoService.update(new PlayerTeamInfo(playersTeamInfo.get(0).getId(), 20, teamService.getAll().get(0)));
        playersTeamInfoExpected.set(0, new PlayerTeamInfo(playersTeamInfo.get(0).getId(), 20, teamService.getAll().get(0)));

        // Assert
        Assertions.assertEquals(playersTeamInfoExpected, playersTeamInfoService.getAll());
    }
}
