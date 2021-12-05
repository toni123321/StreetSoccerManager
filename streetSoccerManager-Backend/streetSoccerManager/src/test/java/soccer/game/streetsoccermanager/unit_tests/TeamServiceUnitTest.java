package soccer.game.streetsoccermanager.unit_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import soccer.game.streetsoccermanager.model.entities.*;
import soccer.game.streetsoccermanager.repository_interfaces.ITeamRepository;
import soccer.game.streetsoccermanager.service.TeamService;
import soccer.game.streetsoccermanager.service_interfaces.ITeamService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
class TeamServiceUnitTest {
    @Mock
    ITeamRepository teamRepository;
    ITeamService teamService;

    @BeforeEach
    public void setUp()  {
        List<Team> teams = List.of(
                new CustomTeam(1l, "Eindhoven 19", new Formation(1l, "1-2-1"),
                        new UserEntity(1l, "erick@gmail.com", "erick12345", "Erick", "Rodriguez", "Erick20", "USER", 0)),
                new OfficialTeam(2l, "Barcelona", new Formation(2l, "2-1-1"), "Ronald Koeman")
        );
        when(teamRepository.getAll()).thenReturn(teams);
        when(teamRepository.get(1l)).thenReturn(teams.get(0));
        when(teamRepository.get(2l)).thenReturn(teams.get(1));
        when(teamRepository.add(new OfficialTeam("Juventus", new Formation(2l, "2-1-1"), "Massimiliano Allegri"))).
                thenReturn(new OfficialTeam(3l, "Juventus", new Formation(2l, "2-1-1"), "Massimiliano Allegri"));
        when(teamRepository.update(new OfficialTeam(2l, "Barcelona", new Formation(2l, "2-1-1"), "Xavi Hernández"))).
                thenReturn(new OfficialTeam(2l, "Barcelona", new Formation(2l, "2-1-1"), "Xavi Hernández"));
        teamService = new TeamService(teamRepository);
    }

    @Test
    void GetAllTeamsSuccessScenario() {
        // Act
        List<Team> teams = teamService.getAll();

        List<Team> teamsExpected = new ArrayList<>();
        teamsExpected.add(new CustomTeam(1l, "Eindhoven 19", new Formation(1l, "1-2-1"),
                new UserEntity(1l, "erick@gmail.com", "erick12345", "Erick", "Rodriguez", "Erick20", "USER", 0)));
        teamsExpected.add(new OfficialTeam(2l, "Barcelona", new Formation(2l, "2-1-1"), "Ronald Koeman"));

        // Assert
        Assertions.assertEquals(teamsExpected, teams);
    }

    @Test
    void GetTeamSuccessScenario() {
        // Act
        Team team = teamService.get(1l);

        // Assert
        Assertions.assertEquals(new CustomTeam(1l, "Eindhoven 19", new Formation(1l, "1-2-1"),
                new UserEntity(1l, "erick@gmail.com", "erick12345", "Erick", "Rodriguez", "Erick20", "USER", 0)), team);
    }

    @Test
    void DeleteTeamSuccessScenario(){
        // Act
        teamService.delete(1l);
        verify(teamRepository).delete(1l);
        when(teamRepository.delete(2l)).thenReturn(true);
        Assertions.assertEquals(true, teamService.delete(2l));
        when(teamRepository.get(3l)).thenReturn(null);
        when(teamRepository.delete(3l)).thenReturn(false);
        Assertions.assertEquals(false, teamService.delete(3l));
    }

    @Test
    void AddTeamSuccessScenario() {
        // Act
        Team newTeam = teamService.add(new OfficialTeam("Juventus", new Formation(2l, "2-1-1"), "Massimiliano Allegri"));
        // Assert
        Assertions.assertEquals(new OfficialTeam(3l, "Juventus", new Formation(2l, "2-1-1"), "Massimiliano Allegri"), newTeam);
        Assertions.assertEquals(null, teamService.add(new OfficialTeam(3l,"Juventus", new Formation(2l, "2-1-1"), "Massimiliano Allegri")));
    }

    @Test
    void UpdateTeamSuccessScenario(){
        // Act
        Team updatedTeam = teamService.update(new OfficialTeam(2l, "Barcelona", new Formation(2l, "2-1-1"), "Xavi Hernández"));
        // Assert
        Assertions.assertEquals(new OfficialTeam(2l, "Barcelona", new Formation(2l, "2-1-1"), "Xavi Hernández"), updatedTeam);
        Assertions.assertEquals(null, teamService.update(new OfficialTeam("Barcelona", new Formation(2l, "2-1-1"), "Xavi Hernández")));
    }
}
