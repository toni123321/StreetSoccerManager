package soccer.game.streetSoccerManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import soccer.game.streetSoccerManager.interfaces.repositoryInterfaces.ITeamRepository;
import soccer.game.streetSoccerManager.interfaces.serviceInterfaces.ITeamService;
import soccer.game.streetSoccerManager.model.Formation;
import soccer.game.streetSoccerManager.model.FrontendUser;
import soccer.game.streetSoccerManager.model.Team;
import soccer.game.streetSoccerManager.model.User;
import soccer.game.streetSoccerManager.repository.Formation.FormationFakeDatabase;
import soccer.game.streetSoccerManager.repository.Team.TeamFakeDatabase;
import soccer.game.streetSoccerManager.repository.User.UserFakeDatabase;
import soccer.game.streetSoccerManager.service.TeamService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TeamServiceTest {

    @Test
    void GetAllTeamsSuccessScenario() {
        // Arrange
        ITeamRepository teamRepository = new TeamFakeDatabase();
        ITeamService teamService = new TeamService(teamRepository);
        FormationFakeDatabase formationFakeDatabase = new FormationFakeDatabase();
        UserFakeDatabase userFakeDatabase = new UserFakeDatabase();

        // Act
        List<Team> teams = teamService.getAll();
        List<Formation> formations = formationFakeDatabase.getAll();
        List<User> users = userFakeDatabase.getAll();
        List<Team> teamsExpected = new ArrayList<>();
        teamsExpected.add(new Team(0, "Real Madrid-Pro", formations.get(0), users.get(0)));
        teamsExpected.add(new Team(1, "newBarca", formations.get(1), users.get(1)));

        // Assert
        Assertions.assertEquals(teamsExpected, teams);

    }

    @Test
    void GetTeamSuccessScenario() {
        // Arrange
        ITeamRepository teamRepository = new TeamFakeDatabase();
        ITeamService teamService = new TeamService(teamRepository);
        Formation formationOne = new Formation(1, "1-2-1");
        Formation formationTwo = new Formation(2, "2-1-1");
        User userOne = new FrontendUser(0, "peter@gmail.com", "123", "pete", 100);
        User userTwo = new FrontendUser(1, "john@gmail.com", "456", "jo", 10);


        // Act
        Team team = teamService.get(1);

        // Assert
        Assertions.assertEquals(new Team(1,  "newBarca", formationTwo, userTwo), team);
    }

    @Test
    void DeleteTeamSuccessScenario(){
        // Arrange
        ITeamRepository teamRepository = new TeamFakeDatabase();
        ITeamService teamService = new TeamService(teamRepository);
        Formation formationOne = new Formation(1, "1-2-1");
        Formation formationTwo = new Formation(2, "2-1-1");
        User userOne = new FrontendUser(0, "peter@gmail.com", "123", "pete", 100);
        User userTwo = new FrontendUser(1, "john@gmail.com", "456", "jo", 10);


        // Act
        teamService.delete(1);
        List<Team> teams = teamService.getAll();
        List<Team> teamsExpected = new ArrayList<>();
        teamsExpected.add(new Team(0, "Real Madrid-Pro", formationOne, userOne));
        teamsExpected.add(new Team(1, "newBarca", formationTwo, userTwo));
        teamsExpected.remove(1);

        // Assert
        Assertions.assertEquals(teamsExpected, teams);
    }

    @Test
    void AddTeamSuccessScenario() {
        // Arrange
        ITeamRepository teamRepository = new TeamFakeDatabase();
        ITeamService teamService = new TeamService(teamRepository);
        Formation formationOne = new Formation(1, "1-2-1");
        Formation formationTwo = new Formation(2, "2-1-1");
        User userOne = new FrontendUser(0, "peter@gmail.com", "123", "pete", 100);
        User userTwo = new FrontendUser(1, "john@gmail.com", "456", "jo", 10);

        // Act

        teamService.add(new Team(2, "Test123", formationOne, userOne));
        List<Team> teams = teamService.getAll();

        List<Team> teamsExpected = new ArrayList<>();
        teamsExpected.add(new Team(0, "Real Madrid-Pro", formationOne, userOne));
        teamsExpected.add(new Team(1, "newBarca", formationTwo, userTwo));
        teamsExpected.add(new Team(2, "Test123", formationOne, userOne));

        // Assert
        Assertions.assertEquals(teamsExpected, teams);
    }

    @Test
    void UpdateTeamSuccessScenario(){
        // Arrange
        ITeamRepository teamRepository = new TeamFakeDatabase();
        ITeamService teamService = new TeamService(teamRepository);
        Formation formationOne = new Formation(1, "1-2-1");
        Formation formationTwo = new Formation(2, "2-1-1");
        User userOne = new FrontendUser(0, "peter@gmail.com", "123", "pete", 100);
        User userTwo = new FrontendUser(1, "john@gmail.com", "456", "jo", 10);

        // Act

        teamService.add(new Team(2, "Test", formationOne, userOne));
        teamService.update(new Team(2, "TestChange", formationOne, userOne));
        List<Team> teams = teamService.getAll();

        List<Team> teamsExpected = new ArrayList<>();
        teamsExpected.add(new Team(0, "Real Madrid-Pro", formationOne, userOne));
        teamsExpected.add(new Team(1, "newBarca", formationTwo, userTwo));
        teamsExpected.add(2, new Team(2, "Test", formationOne, userOne));
        teamsExpected.set(2, new Team(2, "TestChange", formationOne, userOne));

        // Assert
        Assertions.assertEquals(teamsExpected, teams);
    }


}
