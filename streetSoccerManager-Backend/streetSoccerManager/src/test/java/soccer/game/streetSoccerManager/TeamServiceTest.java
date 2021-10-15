package soccer.game.streetSoccerManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import soccer.game.streetSoccerManager.interfaces.repositoryInterfaces.ITeamRepository;
import soccer.game.streetSoccerManager.interfaces.serviceInterfaces.ITeamService;
import soccer.game.streetSoccerManager.model.*;
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
        Formation formationOne = new Formation(1, "1-2-1");
        Formation formationTwo = new Formation(2, "2-1-1");
        User userOne = new FrontendUser(0, "peter@gmail.com", "123", "pete", 100);
        User userTwo = new FrontendUser(1, "john@gmail.com", "456", "jo", 10);
        User admin = new Admin(2, "admin1@gmail.com", "admin1", "Admin1", "Admin1");

        // Act
        List<Team> teams = teamService.getAll();

        List<Team> teamsExpected = new ArrayList<>();
        teamsExpected.add(new Team(0, "Real Madrid-Pro", formationOne, ((FrontendUser) userOne).getNickname(), userOne));
        teamsExpected.add(new Team(1, "Barcelona", formationTwo, "Test", admin));
        teamsExpected.add(new Team(2, "Sevilla", formationTwo, "Test", admin));
        teamsExpected.add(new Team(3, "Juventus", formationTwo, "Test", admin));

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
        User admin = new Admin(2, "admin1@gmail.com", "admin1", "Admin1", "Admin1");


        // Act
        Team team = teamService.get(1);

        // Assert
        Assertions.assertEquals(new Team(1, "Barcelona", formationTwo, "Test", admin), team);
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
        User admin = new Admin(2, "admin1@gmail.com", "admin1", "Admin1", "Admin1");

        // Act
        teamService.delete(1);
        List<Team> teams = teamService.getAll();
        List<Team> teamsExpected = new ArrayList<>();
        teamsExpected.add(new Team(0, "Real Madrid-Pro", formationOne, ((FrontendUser) userOne).getNickname(), userOne));
        teamsExpected.add(new Team(1, "Barcelona", formationTwo, "Test", admin));
        teamsExpected.add(new Team(2, "Sevilla", formationTwo, "Test", admin));
        teamsExpected.add(new Team(3, "Juventus", formationTwo, "Test", admin));
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
        User admin = new Admin(2, "admin1@gmail.com", "admin1", "Admin1", "Admin1");

        // Act

        teamService.add(new Team(4, "Test123", formationOne, ((FrontendUser) userOne).getNickname(), userOne));
        List<Team> teams = teamService.getAll();

        List<Team> teamsExpected = new ArrayList<>();
        teamsExpected.add(new Team(0, "Real Madrid-Pro", formationOne, ((FrontendUser) userOne).getNickname(), userOne));
        teamsExpected.add(new Team(1, "Barcelona", formationTwo, "Test", admin));
        teamsExpected.add(new Team(2, "Sevilla", formationTwo, "Test", admin));
        teamsExpected.add(new Team(3, "Juventus", formationTwo, "Test", admin));
        teamsExpected.add(new Team(4, "Test123", formationOne, ((FrontendUser) userOne).getNickname(), userOne));

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
        User admin = new Admin(2, "admin1@gmail.com", "admin1", "Admin1", "Admin1");

        // Act

        teamService.add(new Team(4, "Test", formationOne, ((FrontendUser) userOne).getNickname(), userOne));
        teamService.update(new Team(4, "TestChange", formationOne, ((FrontendUser) userOne).getNickname(), userOne));
        List<Team> teams = teamService.getAll();

        List<Team> teamsExpected = new ArrayList<>();
        teamsExpected.add(new Team(0, "Real Madrid-Pro", formationOne, ((FrontendUser) userOne).getNickname(), userOne));
        teamsExpected.add(new Team(1, "Barcelona", formationTwo, "Test", admin));
        teamsExpected.add(new Team(2, "Sevilla", formationTwo, "Test", admin));
        teamsExpected.add(new Team(3, "Juventus", formationTwo, "Test", admin));


        teamsExpected.add(4, new Team(4, "Test", formationOne, ((FrontendUser) userOne).getNickname(), userOne));
        teamsExpected.set(4, new Team(4, "TestChange", formationOne, ((FrontendUser) userOne).getNickname(), userOne));

        // Assert
        Assertions.assertEquals(teamsExpected, teams);
    }


}
