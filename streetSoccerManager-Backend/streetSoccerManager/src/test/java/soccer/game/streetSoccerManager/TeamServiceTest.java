package soccer.game.streetSoccerManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import soccer.game.streetSoccerManager.interfaces.repositoryInterfaces.ITeamRepository;
import soccer.game.streetSoccerManager.interfaces.serviceInterfaces.ITeamService;
import soccer.game.streetSoccerManager.model.Team;
import soccer.game.streetSoccerManager.repository.Team.TeamFakeDatabase;
import soccer.game.streetSoccerManager.service.TeamService;

import java.util.List;

@SpringBootTest
public class TeamServiceTest {

    @Test
    void GetTeams() {
        // Arrange
        ITeamRepository teamRepository = new TeamFakeDatabase();
        ITeamService teamService = new TeamService(teamRepository);

        // Act
        List<Team> teams = teamRepository.getAll();
        // Assert
        Assertions.assertEquals(teamRepository.getAll(), teams);

    }

}
