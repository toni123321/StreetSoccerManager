package soccer.game.streetsoccermanager.unit_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import soccer.game.streetsoccermanager.model.entities.*;
import soccer.game.streetsoccermanager.repository_interfaces.IMatchRepository;
import soccer.game.streetsoccermanager.service.MatchService;
import soccer.game.streetsoccermanager.service_interfaces.IMatchService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
class MatchServiceUnitTest {
    @Mock
    IMatchRepository matchRepository;
    IMatchService matchService;

    @BeforeEach
    public void setUp()  {
        List<Match> matches = List.of(
                new FriendlyMatch(1l, new CustomTeam(1l, "Eindhoven 19", new Formation(1l, "1-2-1"),
                        new UserEntity(1l, "erick@gmail.com", "erick12345", "Erick", "Rodriguez", "Erick20", "USER")),
                        new OfficialTeam(2l, "Barcelona", new Formation(2l, "2-1-1"), "Ronald Koeman"),
                        "1:0", "Home team wins", 30),
                new FriendlyMatch(2l, new CustomTeam(1l, "Eindhoven 19", new Formation(1l, "1-2-1"),
                        new UserEntity(1l, "erick@gmail.com", "erick12345", "Erick", "Rodriguez", "Erick20", "USER")),
                        new OfficialTeam(3l, "Juventus", new Formation(2l, "2-1-1"), "Massimiliano Allegri"),
                        "1:1", "Draw", 30)
        );
        when(matchRepository.getAll()).thenReturn(matches);
        when(matchRepository.get(1l)).thenReturn(matches.get(0));
        when(matchRepository.get(2l)).thenReturn(matches.get(1));
        when(matchRepository.add(new FriendlyMatch(
                new OfficialTeam(3l, "Juventus", new Formation(2l, "2-1-1"), "Massimiliano Allegri"),
                new CustomTeam(1l, "Eindhoven 19", new Formation(1l, "1-2-1"),
                        new UserEntity(1l, "erick@gmail.com", "erick12345", "Erick", "Rodriguez", "Erick20", "USER")),
                "2:1", "Home team wins", 30))).
                thenReturn(
                    new FriendlyMatch(3l,
                            new OfficialTeam(3l, "Juventus", new Formation(2l, "2-1-1"), "Massimiliano Allegri"),
                            new CustomTeam(1l, "Eindhoven 19", new Formation(1l, "1-2-1"),
                                    new UserEntity(1l, "erick@gmail.com", "erick12345", "Erick", "Rodriguez", "Erick20", "USER")),
                            "2:1", "Home team wins", 30)
                );
        when(matchRepository.update(new FriendlyMatch(2l, new CustomTeam(1l, "Eindhoven 19", new Formation(1l, "1-2-1"),
                new UserEntity(1l, "erick@gmail.com", "erick12345", "Erick", "Rodriguez", "Erick20", "USER")),
                new OfficialTeam(3l, "Juventus", new Formation(2l, "2-1-1"), "Massimiliano Allegri"),
                "2:2", "Draw", 30))).
                thenReturn(
                        new FriendlyMatch(2l, new CustomTeam(1l, "Eindhoven 19", new Formation(1l, "1-2-1"),
                                new UserEntity(1l, "erick@gmail.com", "erick12345", "Erick", "Rodriguez", "Erick20", "USER")),
                                new OfficialTeam(3l, "Juventus", new Formation(2l, "2-1-1"), "Massimiliano Allegri"),
                                "2:2", "Draw", 30)
                );
        matchService = new MatchService(matchRepository);
    }

    @Test
    void GetAllMatchesSuccessScenario() {
        // Act
        List<Match> matches = matchService.getAll();

        List<Match> matchesExpected = new ArrayList<>();
        matchesExpected.add(new FriendlyMatch(1l, new CustomTeam(1l, "Eindhoven 19", new Formation(1l, "1-2-1"),
                new UserEntity(1l, "erick@gmail.com", "erick12345", "Erick", "Rodriguez", "Erick20", "USER")),
                new OfficialTeam(2l, "Barcelona", new Formation(2l, "2-1-1"), "Ronald Koeman"),
                "1:0", "Home team wins", 30));
        matchesExpected.add(new FriendlyMatch(2l, new CustomTeam(1l, "Eindhoven 19", new Formation(1l, "1-2-1"),
                new UserEntity(1l, "erick@gmail.com", "erick12345", "Erick", "Rodriguez", "Erick20", "USER")),
                new OfficialTeam(3l, "Juventus", new Formation(2l, "2-1-1"), "Massimiliano Allegri"),
                "1:1", "Draw", 30));

        // Assert
        Assertions.assertEquals(matchesExpected, matches);
    }

    @Test
    void GetMatchSuccessScenario() {
        // Act
        Match match = matchService.get(1l);

        // Assert
        Assertions.assertEquals(new FriendlyMatch(1l, new CustomTeam(1l, "Eindhoven 19", new Formation(1l, "1-2-1"),
                new UserEntity(1l, "erick@gmail.com", "erick12345", "Erick", "Rodriguez", "Erick20", "USER")),
                new OfficialTeam(2l, "Barcelona", new Formation(2l, "2-1-1"), "Ronald Koeman"),
                "1:0", "Home team wins", 30), match);
    }

    @Test
    void DeleteMatchSuccessScenario(){
        // Act
        matchService.delete(1l);
        verify(matchRepository).delete(1l);
        when(matchRepository.delete(2l)).thenReturn(true);
        Assertions.assertEquals(true, matchService.delete(2l));
        when(matchRepository.get(3l)).thenReturn(null);
        when(matchRepository.delete(3l)).thenReturn(false);
        Assertions.assertEquals(false, matchService.delete(3l));
    }

    @Test
    void AddMatchSuccessScenario() {
        // Act
        Match newMatch = matchService.add(new FriendlyMatch(
                new OfficialTeam(3l, "Juventus", new Formation(2l, "2-1-1"), "Massimiliano Allegri"),
                new CustomTeam(1l, "Eindhoven 19", new Formation(1l, "1-2-1"),
                        new UserEntity(1l, "erick@gmail.com", "erick12345", "Erick", "Rodriguez", "Erick20", "USER")),
                "2:1", "Home team wins", 30));
        // Assert
        Assertions.assertEquals(new FriendlyMatch(3l,
                new OfficialTeam(3l, "Juventus", new Formation(2l, "2-1-1"), "Massimiliano Allegri"),
                new CustomTeam(1l, "Eindhoven 19", new Formation(1l, "1-2-1"),
                        new UserEntity(1l, "erick@gmail.com", "erick12345", "Erick", "Rodriguez", "Erick20", "USER")),
                "2:1", "Home team wins", 30), newMatch);
        Assertions.assertEquals(null, matchService.add(new FriendlyMatch(3l,
                new OfficialTeam(3l, "Juventus", new Formation(2l, "2-1-1"), "Massimiliano Allegri"),
                new CustomTeam(1l, "Eindhoven 19", new Formation(1l, "1-2-1"),
                        new UserEntity(1l, "erick@gmail.com", "erick12345", "Erick", "Rodriguez", "Erick20", "USER")),
                "2:1", "Home team wins", 30)));
    }

    @Test
    void UpdateMatchSuccessScenario(){
        // Act
        Match updatedMatch = matchService.update(new FriendlyMatch(2l, new CustomTeam(1l, "Eindhoven 19", new Formation(1l, "1-2-1"),
                new UserEntity(1l, "erick@gmail.com", "erick12345", "Erick", "Rodriguez", "Erick20", "USER")),
                new OfficialTeam(3l, "Juventus", new Formation(2l, "2-1-1"), "Massimiliano Allegri"),
                "2:2", "Draw", 30));
        // Assert
        Assertions.assertEquals(new FriendlyMatch(2l, new CustomTeam(1l, "Eindhoven 19", new Formation(1l, "1-2-1"),
                new UserEntity(1l, "erick@gmail.com", "erick12345", "Erick", "Rodriguez", "Erick20", "USER")),
                new OfficialTeam(3l, "Juventus", new Formation(2l, "2-1-1"), "Massimiliano Allegri"),
                "2:2", "Draw", 30), updatedMatch);
        Assertions.assertEquals(null, matchService.update(new FriendlyMatch(new CustomTeam(1l, "Eindhoven 19", new Formation(1l, "1-2-1"),
                new UserEntity(1l, "erick@gmail.com", "erick12345", "Erick", "Rodriguez", "Erick20", "USER")),
                new OfficialTeam(3l, "Juventus", new Formation(2l, "2-1-1"), "Massimiliano Allegri"),
                "2:2", "Draw", 30)));
    }
}
