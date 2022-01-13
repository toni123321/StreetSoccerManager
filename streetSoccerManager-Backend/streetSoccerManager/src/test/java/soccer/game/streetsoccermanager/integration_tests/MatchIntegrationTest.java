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
import soccer.game.streetsoccermanager.service.MatchService;
import soccer.game.streetsoccermanager.service.TeamService;
import soccer.game.streetsoccermanager.service.UserService;

import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
class MatchIntegrationTest {
    //Arrange
    @Autowired
    MatchService matchService;
    @Autowired
    TeamService teamService;
    @Autowired
    UserService userService;
    @Autowired
    FormationService formationService;

    List<Match> matches = new ArrayList<>();
    List<Match> matchesExpected = new ArrayList<>();

    @BeforeEach
    void clearDB(){
        // Clear
        matchService.deleteAll();
        teamService.deleteAll();
        formationService.deleteAll();
        userService.deleteAll();

        matchesExpected.clear();

        try {
            formationService.add(new Formation("1-2-1"));
            formationService.add(new Formation("2-1-1"));

            userService.add(new UserEntity("peter@gmail.com", "Peter@123", "Peter", "Petrov", "pesho", "USER"));
            teamService.add(new CustomTeam("Soccer01", formationService.getAll().get(0), userService.getAll().get(0)));
            teamService.add(new OfficialTeam("Barcelona", formationService.getAll().get(0), "Pep Guardiola"));
            teamService.add(new OfficialTeam("Real Madrid", formationService.getAll().get(0), "Carlo Ancelotti"));


            matchService.add(new FriendlyMatch(teamService.getAll().get(0), teamService.getAll().get(1), "1:0", "text", 30));
            matchService.add(new FriendlyMatch(teamService.getAll().get(0), teamService.getAll().get(2), "1:1", "text", 30));
            matches = matchService.getAll();
            matchesExpected.add(new FriendlyMatch(matches.get(0).getId(), teamService.getAll().get(0), teamService.getAll().get(1), "1:0", "text", 30));
            matchesExpected.add(new FriendlyMatch(matches.get(1).getId(), teamService.getAll().get(0), teamService.getAll().get(2), "1:1", "text", 30));

        }
        catch(EntryNotValidException e){

        }


    }

    @Test
    void getAllMatchesSuccessScenario() {
        // Assert
        Assertions.assertEquals(matchesExpected, matchService.getAll()) ;
    }


    @Test
    void GetMatchSuccessScenario() {
        // Act
        Match match = matchService.get(matches.get(0).getId());

        // Assert
        Assertions.assertEquals(new FriendlyMatch(matches.get(0).getId(), teamService.getAll().get(0), teamService.getAll().get(1), "1:0", "text", 30), match);
    }

    @Test
    void DeleteMatchSuccessScenario(){
        // Act
        matchService.delete(matches.get(0).getId());
        matchesExpected.remove(0);

        // Assert
        Assertions.assertEquals(matchesExpected, matchService.getAll());
    }

    @Test
    void AddMatchSuccessScenario() {
        // Act
        matchService.add(new FriendlyMatch(teamService.getAll().get(2), teamService.getAll().get(0), "2:1", "text", 30));
        matches = matchService.getAll();
        Long lastIndexId = matches.get(matches.size() - 1).getId();
        matchesExpected.add(new FriendlyMatch(lastIndexId, teamService.getAll().get(2), teamService.getAll().get(0), "2:1", "text", 30));

        // Assert
        Assertions.assertEquals(matchesExpected, matchService.getAll());
    }

    @Test
    void UpdateMatchSuccessScenario(){
        // Act
        matchService.update(new FriendlyMatch(matches.get(0).getId(), teamService.getAll().get(0), teamService.getAll().get(1), "1:0", "Home tem win", 30));
        matchesExpected.set(0, new FriendlyMatch(matches.get(0).getId(), teamService.getAll().get(0), teamService.getAll().get(1), "1:0", "Home team win", 30));

        // Assert
        Assertions.assertEquals(matchesExpected, matchService.getAll());
    }
}
