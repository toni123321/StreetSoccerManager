package soccer.game.streetSoccerManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import soccer.game.streetSoccerManager.interfaces.repositoryInterfaces.ITeamRepository;
import soccer.game.streetSoccerManager.interfaces.serviceInterfaces.ITeamService;
import soccer.game.streetSoccerManager.model.Formation;
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
    void GetTeams() {
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
    void GetTeam() {
        // Arrange
        ITeamRepository teamRepository = new TeamFakeDatabase();
        ITeamService teamService = new TeamService(teamRepository);

        FormationFakeDatabase formationFakeDatabase = new FormationFakeDatabase();
        UserFakeDatabase userFakeDatabase = new UserFakeDatabase();

        // Act
        Team team = teamService.get(1);
        List<Formation> formations = formationFakeDatabase.getAll();
        List<User> users = userFakeDatabase.getAll();

        // Assert
        Assertions.assertEquals(new Team(1,  "newBarca", formations.get(1), users.get(1)), team);
    }

    @Test
    void DeleteTeam(){
        // Arrange
        ITeamRepository teamRepository = new TeamFakeDatabase();
        ITeamService teamService = new TeamService(teamRepository);
        FormationFakeDatabase formationFakeDatabase = new FormationFakeDatabase();
        UserFakeDatabase userFakeDatabase = new UserFakeDatabase();

        // Act
        teamService.delete(1);
        List<Team> teams = teamService.getAll();
        List<Formation> formations = formationFakeDatabase.getAll();
        List<User> users = userFakeDatabase.getAll();
        List<Team> teamsExpected = new ArrayList<>();
        teamsExpected.add(new Team(0, "Real Madrid-Pro", formations.get(0), users.get(0)));
        teamsExpected.add(new Team(1, "newBarca", formations.get(1), users.get(1)));
        teamsExpected.remove(1);

        // Assert
        Assertions.assertEquals(teamsExpected, teams);
    }

    @Test
    void AddTeam() {
        // Arrange
        ITeamRepository teamRepository = new TeamFakeDatabase();
        ITeamService teamService = new TeamService(teamRepository);
        FormationFakeDatabase formationFakeDatabase = new FormationFakeDatabase();
        UserFakeDatabase userFakeDatabase = new UserFakeDatabase();

        // Act
        List<Formation> formations = formationFakeDatabase.getAll();
        List<User> users = userFakeDatabase.getAll();
        teamService.add(new Team(2, "Test123", formations.get(0), users.get(0)));
        List<Team> teams = teamService.getAll();

        List<Team> teamsExpected = new ArrayList<>();
        teamsExpected.add(new Team(0, "Real Madrid-Pro", formations.get(0), users.get(0)));
        teamsExpected.add(new Team(1, "newBarca", formations.get(1), users.get(1)));
        teamsExpected.add(new Team(2, "Test123", formations.get(0), users.get(0)));

        // Assert
        Assertions.assertEquals(teamsExpected, teams);
    }

    @Test
    void UpdateTeam(){
        // Arrange
        ITeamRepository teamRepository = new TeamFakeDatabase();
        ITeamService teamService = new TeamService(teamRepository);
        FormationFakeDatabase formationFakeDatabase = new FormationFakeDatabase();
        UserFakeDatabase userFakeDatabase = new UserFakeDatabase();

        // Act
        List<Formation> formations = formationFakeDatabase.getAll();
        List<User> users = userFakeDatabase.getAll();
        teamService.add(new Team(2, "Test123", formations.get(0), users.get(0)));
        teamService.update(new Team(2, "TestChange", formations.get(0), users.get(0)));
        List<Team> teams = teamService.getAll();

        List<Team> teamsExpected = new ArrayList<>();
        teamsExpected.add(new Team(0, "Real Madrid-Pro", formations.get(0), users.get(0)));
        teamsExpected.add(new Team(1, "newBarca", formations.get(1), users.get(1)));
        teamsExpected.add(new Team(2, "Test123", formations.get(0), users.get(0)));
        teamsExpected.set(2, new Team(2, "TestChange", formations.get(0), users.get(0)));

        // Assert
        Assertions.assertEquals(teamsExpected, teams);
    }


}
