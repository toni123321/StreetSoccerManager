package soccer.game.streetsoccermanager.integration_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import soccer.game.streetsoccermanager.exceptions.EntryNotValidException;
import soccer.game.streetsoccermanager.model.entities.*;
import soccer.game.streetsoccermanager.service.FormationService;
import soccer.game.streetsoccermanager.service.TeamService;
import soccer.game.streetsoccermanager.service.UserService;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
class TeamIntegrationTest {
    //Arrange
    @Autowired
    TeamService teamService;
    List<Team> teams = new ArrayList<>();
    List<Team> teamsExpected = new ArrayList<>();
    @Autowired
    UserService userService;
    @Autowired
    FormationService formationService;

    @BeforeEach
    void clearDB() {
        // Clear
        formationService.deleteAll();
        userService.deleteAll();
        teamService.deleteAll();
        teamsExpected.clear();


        try {
            // Add
            formationService.add(new Formation("1-2-1"));
            formationService.add(new Formation("2-1-1"));
            userService.add(new UserEntity("peter@gmail.com", "Peter@123", "Peter", "Petrov", "pesho", "USER"));

            teamService.add(new CustomTeam("Soccer01", formationService.getAll().get(0), userService.getAll().get(0)));
            teamService.add(new OfficialTeam("Barcelona", formationService.getAll().get(0), "Pep Guardiola"));
            teams = teamService.getAll();
            teamsExpected.add(new CustomTeam(teams.get(0).getId(), "Soccer01", formationService.getAll().get(0), userService.getAll().get(0)));
            teamsExpected.add(new OfficialTeam(teams.get(1).getId(), "Barcelona", formationService.getAll().get(0), "Pep Guardiola"));
        }
        catch(EntityExistsException | EntryNotValidException e){

        }
    }

    @Test
    void getAllTeamsSuccessScenario() {
        // Assert
        Assertions.assertEquals(teamsExpected, teamService.getAll()) ;
    }


    @Test
    void GetTeamSuccessScenario() {
        // Act
        Team team = teamService.get(teams.get(0).getId());

        // Assert
        Assertions.assertEquals(new CustomTeam(teams.get(0).getId(), "Soccer01", formationService.getAll().get(0), userService.getAll().get(0) ), team);
    }

    @Test
    void DeleteTeamSuccessScenario(){
        // Act
        teamService.delete(teams.get(0).getId());
        teamsExpected.remove(0);

        // Assert
        Assertions.assertEquals(teamsExpected, teamService.getAll());
    }

    @Test
    void AddTeamSuccessScenario() {
        // Act
        teamService.add(new OfficialTeam("Juventus", formationService.getAll().get(0), "Ivan Stoyanov"));
        teams = teamService.getAll();
        Long lastIndexId = teams.get(teams.size() - 1).getId();
        teamsExpected.add(new OfficialTeam(lastIndexId, "Juventus", formationService.getAll().get(0), "Ivan Stoyanov"));

        // Assert
        Assertions.assertEquals(teamsExpected, teamService.getAll());
    }

    @Test
    void UpdateTeamSuccessScenario(){
        // Act
        teamService.update(new CustomTeam(teams.get(0).getId(), "Soccer-01", formationService.getAll().get(0), userService.getAll().get(0)));
        teamsExpected.set(0, new CustomTeam(teams.get(0).getId(), "Soccer-01", formationService.getAll().get(0), userService.getAll().get(0)));

        // Assert
        Assertions.assertEquals(teamsExpected, teamService.getAll());
    }
}
