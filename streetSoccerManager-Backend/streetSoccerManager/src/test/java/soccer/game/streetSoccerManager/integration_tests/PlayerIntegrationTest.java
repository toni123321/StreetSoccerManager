package soccer.game.streetSoccerManager.integration_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import soccer.game.streetSoccerManager.model.entities.*;
import soccer.game.streetSoccerManager.service.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
public class PlayerIntegrationTest {
    //Arrange
    @Autowired
    PlayerService playerService;
    List<Player> players = new ArrayList<>();
    List<Player> playersExpected = new ArrayList<>();
    @Autowired
    PlayerPersonalInfoService playerPersonalInfoService;
    @Autowired
    PlayerPositionInfoService playerPositionInfoService;
    @Autowired
    PlayerAdditionalInfoService playerAdditionalInfoService;
    @Autowired
    PlayerTeamInfoService playerTeamInfoService;
    @Autowired
    PositionService positionService;
    @Autowired
    TeamService teamService;
    @Autowired
    PlayerStatsService playerStatsService;
    @Autowired
    UserService userService;
    @Autowired
    FormationService formationService;

    @BeforeEach
    void clearDB() {
        // Clear

        playerService.deleteAll();
        playerPersonalInfoService.deleteAll();


        playerPositionInfoService.deleteAll();
        positionService.deleteAll();


        playerAdditionalInfoService.deleteAll();
        playerStatsService.deleteAll();

        playerTeamInfoService.deleteAll();
        userService.deleteAll();
        teamService.deleteAll();
        formationService.deleteAll();


        playersExpected.clear();



        // Add

        positionService.add(new Position("ATACK", "ST"));
        positionService.add(new Position("ATACK", "LW"));
        positionService.add(new Position("MID", "CM"));

        playerStatsService.add(new PlayerStats(60, 70));
        playerStatsService.add(new PlayerStats(70, 75));
        playerStatsService.add(new PlayerStats(80, 85));

        userService.add(new UserEntity("peter@gmail.com", "123", "Peter", "Petrov", "pesho"));
        formationService.add(new Formation("1-2-1"));

        teamService.add(new CustomTeam("Barca 2020", formationService.getAll().get(0), userService.getAll().get(0)));


        playerPersonalInfoService.add(new PlayerPersonalInfo("Lionel", "Messi", new GregorianCalendar(1997, 5, 15)));
        playerPersonalInfoService.add(new PlayerPersonalInfo("Cristiano", "Ronaldo", new GregorianCalendar(1995, 5, 15)));
        playerPersonalInfoService.add(new PlayerPersonalInfo("Juan", "Mata", new GregorianCalendar(1979, 5, 15)));


        playerPositionInfoService.add(new PlayerPositionInfo(positionService.getAll().get(0), positionService.getAll().get(0), true));
        playerPositionInfoService.add(new PlayerPositionInfo(positionService.getAll().get(1), positionService.getAll().get(1), false));
        playerPositionInfoService.add(new PlayerPositionInfo(positionService.getAll().get(2), positionService.getAll().get(2), true));


        playerTeamInfoService.add(new PlayerTeamInfo(2, teamService.getAll().get(0)));
        playerTeamInfoService.add(new PlayerTeamInfo(10, teamService.getAll().get(0)));
        playerTeamInfoService.add(new PlayerTeamInfo(6, teamService.getAll().get(0)));


        playerAdditionalInfoService.add(new PlayerAdditionalInfo(150, playerStatsService.getAll().get(0)));
        playerAdditionalInfoService.add(new PlayerAdditionalInfo(120, playerStatsService.getAll().get(1)));
        playerAdditionalInfoService.add(new PlayerAdditionalInfo(250, playerStatsService.getAll().get(2)));



        playerService.add(new Player(playerPersonalInfoService.getAll().get(0), playerPositionInfoService.getAll().get(0), playerTeamInfoService.getAll().get(0), playerAdditionalInfoService.getAll().get(0)));
        playerService.add(new Player(playerPersonalInfoService.getAll().get(1), playerPositionInfoService.getAll().get(1), playerTeamInfoService.getAll().get(1), playerAdditionalInfoService.getAll().get(1)));

        players = playerService.getAll();
        playersExpected.add(new Player(players.get(0).getId(), playerPersonalInfoService.getAll().get(0), playerPositionInfoService.getAll().get(0), playerTeamInfoService.getAll().get(0), playerAdditionalInfoService.getAll().get(0)));
        playersExpected.add(new Player(players.get(1).getId(), playerPersonalInfoService.getAll().get(1), playerPositionInfoService.getAll().get(1), playerTeamInfoService.getAll().get(1), playerAdditionalInfoService.getAll().get(1)));

    }

    @Test
    void getAllFormationsSuccessScenario() {
        // Assert
        Assertions.assertEquals(playersExpected, playerService.getAll()) ;
    }


    @Test
    void GetFormationSuccessScenario() {
        // Act
        Player player = playerService.get(players.get(0).getId());

        // Assert
        Assertions.assertEquals(new Player(players.get(0).getId(), playerPersonalInfoService.getAll().get(0), playerPositionInfoService.getAll().get(0), playerTeamInfoService.getAll().get(0), playerAdditionalInfoService.getAll().get(0)), player);
    }

    @Test
    void DeleteFormationSuccessScenario(){
        // Act
        playerService.delete(players.get(0).getId());
        playersExpected.remove(0);

        // Assert
        Assertions.assertEquals(playersExpected, playerService.getAll());
    }

    @Test
    void AddFormationSuccessScenario() {
        // Act

        playerService.add(new Player(playerPersonalInfoService.getAll().get(2), playerPositionInfoService.getAll().get(2), playerTeamInfoService.getAll().get(2), playerAdditionalInfoService.getAll().get(2)));
        players = playerService.getAll();
        Long lastIndexId = players.get(players.size() - 1).getId();
        playersExpected.add(new Player(lastIndexId, playerPersonalInfoService.getAll().get(2), playerPositionInfoService.getAll().get(2), playerTeamInfoService.getAll().get(2), playerAdditionalInfoService.getAll().get(2)));

        // Assert
        Assertions.assertEquals(playersExpected, playerService.getAll());
    }

    @Test
    void UpdateTeamSuccessScenario(){
        // Act
        playerService.update(new Player(players.get(0).getId(), playerPersonalInfoService.getAll().get(2), playerPositionInfoService.getAll().get(0), playerTeamInfoService.getAll().get(0), playerAdditionalInfoService.getAll().get(0)));
        playersExpected.set(0, new Player(players.get(0).getId(),playerPersonalInfoService.getAll().get(2), playerPositionInfoService.getAll().get(0), playerTeamInfoService.getAll().get(0), playerAdditionalInfoService.getAll().get(0)));

        // Assert
        Assertions.assertEquals(playersExpected, playerService.getAll());
    }
}

