package soccer.game.streetSoccerManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import soccer.game.streetSoccerManager.model.entities.*;
import soccer.game.streetSoccerManager.repository.repositories.Formation.FormationStubDatabase;
import soccer.game.streetSoccerManager.repository.repositories.User.UserStubDatabase;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IFormationRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.ITeamRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IUserRepository;
import soccer.game.streetSoccerManager.service.serviceInterfaces.ITeamService;
import soccer.game.streetSoccerManager.repository.repositories.Team.TeamStubDatabase;
import soccer.game.streetSoccerManager.service.TeamService;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest

public class TeamServiceTest {

    @Test
    void GetAllTeamsSuccessScenario() {
        // Arrange
        ITeamRepository teamRepository = new TeamStubDatabase();
        ITeamService teamService = new TeamService(teamRepository);
        Formation formationOne = new Formation(1l, "1-2-1");
        Formation formationTwo = new Formation(2l, "2-1-1");
        User userOne = new EndUser(0l, "peter@gmail.com", "123", "pete", 100);
        User userTwo = new EndUser(1l, "john@gmail.com", "456", "jo", 10);
        User admin = new Admin(2l, "admin1@gmail.com", "admin1", "Admin1", "Admin1");

        // Act
        List<Team> teams = teamService.getAll();

        List<Team> teamsExpected = new ArrayList<>();
        teamsExpected.add(new CustomTeam(0l, "Real Madrid-Pro", formationOne, userOne));
        teamsExpected.add(new OfficialTeam(1l,  "Barcelona", formationTwo, "Manager1"));
        teamsExpected.add(new OfficialTeam(2l,  "Sevilla", formationTwo, "Manager2"));
        teamsExpected.add(new OfficialTeam(3l,  "Juventus", formationTwo, "Manager3"));

        // Assert
        Assertions.assertEquals(teamsExpected, teams);

    }

    @Test

    void GetCustomTeamsSuccessScenario() {
        // Arrange
        ITeamRepository teamRepository = new TeamStubDatabase();
        ITeamService teamService = new TeamService(teamRepository);
        Formation formationOne = new Formation(1l, "1-2-1");
        Formation formationTwo = new Formation(2l, "2-1-1");
        User userOne = new EndUser(0l, "peter@gmail.com", "123", "pete", 100);
        User userTwo = new EndUser(1l, "john@gmail.com", "456", "jo", 10);
        User admin = new Admin(2l, "admin1@gmail.com", "admin1", "Admin1", "Admin1");
        IFormationRepository formationRepository = new FormationStubDatabase();
        IUserRepository userRepository = new UserStubDatabase();

        // Act
        List<Team> teams = teamService.getCustomTeams();

        List<Team> teamsExpected = new ArrayList<>();
        teamsExpected.add(new CustomTeam(0l, "Real Madrid-Pro", formationRepository.get(1l), userRepository.get(0l)));
        
        // Assert
        Assertions.assertEquals(teamsExpected, teams);

    }

    @Test
    void GetOfficialTeamsSuccessScenario() {
        // Arrange
        ITeamRepository teamRepository = new TeamStubDatabase();
        ITeamService teamService = new TeamService(teamRepository);
        Formation formationOne = new Formation(1l, "1-2-1");
        Formation formationTwo = new Formation(2l, "2-1-1");
        User userOne = new EndUser(0l, "peter@gmail.com", "123", "pete", 100);
        User userTwo = new EndUser(1l, "john@gmail.com", "456", "jo", 10);
        User admin = new Admin(2l, "admin1@gmail.com", "admin1", "Admin1", "Admin1");
        IFormationRepository formationRepository = new FormationStubDatabase();
        IUserRepository userRepository = new UserStubDatabase();

        // Act
        List<Team> teams = teamService.getOfficialTeams();

        List<Team> teamsExpected = new ArrayList<>();
        teamsExpected.add(new OfficialTeam(1l,  "Barcelona", formationRepository.get(2l), "Manager1"));
        teamsExpected.add(new OfficialTeam(2l,  "Sevilla", formationRepository.get(2l), "Manager2"));
        teamsExpected.add(new OfficialTeam(3l,  "Juventus", formationRepository.get(2l), "Manager3"));

        // Assert
        Assertions.assertEquals(teamsExpected, teams);

    }

    @Test
    void GetTeamSuccessScenario() {
        // Arrange
        ITeamRepository teamRepository = new TeamStubDatabase();
        ITeamService teamService = new TeamService(teamRepository);
        Formation formationOne = new Formation(1l, "1-2-1");
        Formation formationTwo = new Formation(2l, "2-1-1");
        User userOne = new EndUser(0l, "peter@gmail.com", "123", "pete", 100);
        User userTwo = new EndUser(1l, "john@gmail.com", "456", "jo", 10);
        User admin = new Admin(2l, "admin1@gmail.com", "admin1", "Admin1", "Admin1");

        // Act
        Team team = teamService.get(1l);

        // Assert
        Assertions.assertEquals(new OfficialTeam(1l,  "Barcelona", formationTwo, "Manager1"), team);
    }

    @Test
    void DeleteTeamSuccessScenario(){
        // Arrange
        ITeamRepository teamRepository = new TeamStubDatabase();
        ITeamService teamService = new TeamService(teamRepository);
        Formation formationOne = new Formation(1l, "1-2-1");
        Formation formationTwo = new Formation(2l, "2-1-1");
        User userOne = new EndUser(0l, "peter@gmail.com", "123", "pete", 100);
        User userTwo = new EndUser(1l, "john@gmail.com", "456", "jo", 10);
        User admin = new Admin(2l, "admin1@gmail.com", "admin1", "Admin1", "Admin1");

        // Act
        teamService.delete(1l);
        List<Team> teams = teamService.getAll();
        List<Team> teamsExpected = new ArrayList<>();
        teamsExpected.add(new CustomTeam(0l, "Real Madrid-Pro", formationOne, userOne));
        teamsExpected.add(new OfficialTeam(1l,  "Barcelona", formationTwo, "Manager1"));
        teamsExpected.add(new OfficialTeam(2l,  "Sevilla", formationTwo, "Manager2"));
        teamsExpected.add(new OfficialTeam(3l,  "Juventus", formationTwo, "Manager3"));
        teamsExpected.remove(1);

        // Assert
        Assertions.assertEquals(teamsExpected, teams);
    }

    @Test
    void AddTeamSuccessScenario() {
        // Arrange
        ITeamRepository teamRepository = new TeamStubDatabase();
        ITeamService teamService = new TeamService(teamRepository);
        Formation formationOne = new Formation(1l, "1-2-1");
        Formation formationTwo = new Formation(2l, "2-1-1");
        User userOne = new EndUser(0l, "peter@gmail.com", "123", "pete", 100);
        User userTwo = new EndUser(1l, "john@gmail.com", "456", "jo", 10);
        User admin = new Admin(2l, "admin1@gmail.com", "admin1", "Admin1", "Admin1");

        // Act

        teamService.add(new CustomTeam(4l, "Test123", formationOne, userTwo));
        List<Team> teams = teamService.getAll();

        List<Team> teamsExpected = new ArrayList<>();
        teamsExpected.add(new CustomTeam(0l, "Real Madrid-Pro", formationOne, userOne));
        teamsExpected.add(new OfficialTeam(1l,  "Barcelona", formationTwo, "Manager1"));
        teamsExpected.add(new OfficialTeam(2l,  "Sevilla", formationTwo, "Manager2"));
        teamsExpected.add(new OfficialTeam(3l,  "Juventus", formationTwo, "Manager3"));

        teamsExpected.add(new CustomTeam(4l, "Test123", formationOne, userTwo));

        // Assert
        Assertions.assertEquals(teamsExpected, teams);
    }

    @Test
    void UpdateTeamSuccessScenario(){
        // Arrange
        ITeamRepository teamRepository = new TeamStubDatabase();
        ITeamService teamService = new TeamService(teamRepository);
        Formation formationOne = new Formation(1l, "1-2-1");
        Formation formationTwo = new Formation(2l, "2-1-1");
        User userOne = new EndUser(0l, "peter@gmail.com", "123", "pete", 100);
        User userTwo = new EndUser(1l, "john@gmail.com", "456", "jo", 10);
        User admin = new Admin(2l, "admin1@gmail.com", "admin1", "Admin1", "Admin1");

        // Act
        teamService.update(new OfficialTeam(3l,  "Parma", formationTwo, "Manager3"));
        List<Team> teams = teamService.getAll();

        List<Team> teamsExpected = new ArrayList<>();
        teamsExpected.add(new CustomTeam(0l, "Real Madrid-Pro", formationOne, userOne));
        teamsExpected.add(new OfficialTeam(1l,  "Barcelona", formationTwo, "Manager1"));
        teamsExpected.add(new OfficialTeam(2l,  "Sevilla", formationTwo, "Manager2"));
        teamsExpected.add(new OfficialTeam(3l,  "Juventus", formationTwo, "Manager3"));

        teamsExpected.set(3, new OfficialTeam(3l,  "Parma", formationTwo, "Manager3"));

        // Assert
        Assertions.assertEquals(teamsExpected, teams);
    }


}
