package soccer.game.streetSoccerManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import soccer.game.streetSoccerManager.model.entities.FriendlyMatch;
import soccer.game.streetSoccerManager.model.entities.Match;
import soccer.game.streetSoccerManager.model.entities.MatchInfo;
import soccer.game.streetSoccerManager.repository.repositories.Match.MatchFakeDatabase;
import soccer.game.streetSoccerManager.repository.repositories.MatchInfo.MatchInfoFakeDatabase;
import soccer.game.streetSoccerManager.repository.repositories.MatchStatistic.MatchStatisticFakeDatabase;
import soccer.game.streetSoccerManager.repository.repositories.Team.TeamFakeDatabase;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IMatchInfoRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IMatchRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IMatchStatisticRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.ITeamRepository;
import soccer.game.streetSoccerManager.service.MatchInfoService;
import soccer.game.streetSoccerManager.service.MatchService;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IMatchInfoService;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IMatchService;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MatchServiceTest {
    @Test
    void GetAllMatchesSuccessScenario() {
        // Arrange
        IMatchRepository matchRepository = new MatchFakeDatabase();
        IMatchService matchService = new MatchService(matchRepository);

        IMatchInfoRepository matchInfoRepository = new MatchInfoFakeDatabase();
        IMatchStatisticRepository matchStatisticRepository = new MatchStatisticFakeDatabase();

        // Act
        List<Match> matches = matchService.getAll();

        List<Match> matchesExpected = new ArrayList<>();
        matchesExpected.add(new FriendlyMatch(1l, matchInfoRepository.get(1l), matchStatisticRepository.get(1l)));
        matchesExpected.add(new FriendlyMatch(2l, matchInfoRepository.get(2l), matchStatisticRepository.get(2l)));


        // Assert
        Assertions.assertEquals(matchesExpected, matches);

    }

    @Test
    void GetMatchSuccessScenario() {
        // Arrange
        IMatchRepository matchRepository = new MatchFakeDatabase();
        IMatchService matchService = new MatchService(matchRepository);

        IMatchInfoRepository matchInfoRepository = new MatchInfoFakeDatabase();
        IMatchStatisticRepository matchStatisticRepository = new MatchStatisticFakeDatabase();

        // Act
        Match match = matchService.get(1l);

        // Assert
        Assertions.assertEquals(new FriendlyMatch(1l, matchInfoRepository.get(1l), matchStatisticRepository.get(1l)), match);
    }

    @Test
    void DeleteMatchSuccessScenario(){
        // Arrange
        IMatchRepository matchRepository = new MatchFakeDatabase();
        IMatchService matchService = new MatchService(matchRepository);

        IMatchInfoRepository matchInfoRepository = new MatchInfoFakeDatabase();
        IMatchStatisticRepository matchStatisticRepository = new MatchStatisticFakeDatabase();

        // Act
        matchService.delete(1l);
        List<Match> matches = matchService.getAll();

        List<Match> matchesExpected = new ArrayList<>();
        matchesExpected.add(new FriendlyMatch(1l, matchInfoRepository.get(1l), matchStatisticRepository.get(1l)));
        matchesExpected.add(new FriendlyMatch(2l, matchInfoRepository.get(2l), matchStatisticRepository.get(2l)));
        matchesExpected.remove(0);

        // Assert
        Assertions.assertEquals(matchesExpected, matches);
    }

    @Test
    void AddMatchSuccessScenario() {
        // Arrange
        IMatchRepository matchRepository = new MatchFakeDatabase();
        IMatchService matchService = new MatchService(matchRepository);

        IMatchInfoRepository matchInfoRepository = new MatchInfoFakeDatabase();
        IMatchStatisticRepository matchStatisticRepository = new MatchStatisticFakeDatabase();

        // Act
        matchService.add(new FriendlyMatch(3l, matchInfoRepository.get(3l), matchStatisticRepository.get(3l)));
        List<Match> matches = matchService.getAll();

        List<Match> matchesExpected = new ArrayList<>();
        matchesExpected.add(new FriendlyMatch(1l, matchInfoRepository.get(1l), matchStatisticRepository.get(1l)));
        matchesExpected.add(new FriendlyMatch(2l, matchInfoRepository.get(2l), matchStatisticRepository.get(2l)));
        matchesExpected.add(new FriendlyMatch(3l, matchInfoRepository.get(3l), matchStatisticRepository.get(3l)));

        // Assert
        Assertions.assertEquals(matchesExpected, matches);
    }

    @Test
    void UpdateMatchSuccessScenario(){
        // Arrange
        IMatchRepository matchRepository = new MatchFakeDatabase();
        IMatchService matchService = new MatchService(matchRepository);

        IMatchInfoRepository matchInfoRepository = new MatchInfoFakeDatabase();
        IMatchStatisticRepository matchStatisticRepository = new MatchStatisticFakeDatabase();

        // Act
        matchService.update(new FriendlyMatch(1l, matchInfoRepository.get(1l), matchStatisticRepository.get(2l)));
        List<Match> matches = matchService.getAll();

        List<Match> matchesExpected = new ArrayList<>();
        matchesExpected.add(new FriendlyMatch(1l, matchInfoRepository.get(1l), matchStatisticRepository.get(1l)));
        matchesExpected.add(new FriendlyMatch(2l, matchInfoRepository.get(2l), matchStatisticRepository.get(2l)));
        matchesExpected.set(0, new FriendlyMatch(1l, matchInfoRepository.get(1l), matchStatisticRepository.get(2l)));

        // Assert
        Assertions.assertEquals(matchesExpected, matches);
    }
}
