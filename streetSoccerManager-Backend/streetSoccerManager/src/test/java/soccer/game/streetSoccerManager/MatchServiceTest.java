//package soccer.game.streetSoccerManager;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import soccer.game.streetSoccerManager.model.entities.FriendlyMatch;
//import soccer.game.streetSoccerManager.model.entities.Match;
//import soccer.game.streetSoccerManager.repository.repositories.Match.MatchStubDatabase;
//import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IMatchRepository;
//import soccer.game.streetSoccerManager.service.MatchService;
//import soccer.game.streetSoccerManager.serviceInterfaces.IMatchService;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@SpringBootTest
//public class MatchServiceTest {
//    @Test
//    void GetAllMatchesSuccessScenario() {
//        // Arrange
//        IMatchRepository matchRepository = new MatchStubDatabase();
//        IMatchService matchService = new MatchService(matchRepository);
//
//
//        // Act
//        List<Match> matches = matchService.getAll();
//
//        List<Match> matchesExpected = new ArrayList<>();
//        matchesExpected.add(new FriendlyMatch(1l, matchInfoRepository.get(1l), matchStatisticRepository.get(1l)));
//        matchesExpected.add(new FriendlyMatch(2l, matchInfoRepository.get(2l), matchStatisticRepository.get(2l)));
//
//
//        // Assert
//        Assertions.assertEquals(matchesExpected, matches);
//
//    }
//
//    @Test
//    void GetMatchSuccessScenario() {
//        // Arrange
//        IMatchRepository matchRepository = new MatchStubDatabase();
//        IMatchService matchService = new MatchService(matchRepository);
//
//        IMatchInfoRepository matchInfoRepository = new MatchInfoStubDatabase();
//        IMatchStatisticRepository matchStatisticRepository = new MatchStatisticStubDatabase();
//
//        // Act
//        Match match = matchService.get(1l);
//
//        // Assert
//        Assertions.assertEquals(new FriendlyMatch(1l, matchInfoRepository.get(1l), matchStatisticRepository.get(1l)), match);
//    }
//
//    @Test
//    void DeleteMatchSuccessScenario(){
//        // Arrange
//        IMatchRepository matchRepository = new MatchStubDatabase();
//        IMatchService matchService = new MatchService(matchRepository);
//
//        IMatchInfoRepository matchInfoRepository = new MatchInfoStubDatabase();
//        IMatchStatisticRepository matchStatisticRepository = new MatchStatisticStubDatabase();
//
//        // Act
//        matchService.delete(1l);
//        List<Match> matches = matchService.getAll();
//
//        List<Match> matchesExpected = new ArrayList<>();
//        matchesExpected.add(new FriendlyMatch(1l, matchInfoRepository.get(1l), matchStatisticRepository.get(1l)));
//        matchesExpected.add(new FriendlyMatch(2l, matchInfoRepository.get(2l), matchStatisticRepository.get(2l)));
//        matchesExpected.remove(0);
//
//        // Assert
//        Assertions.assertEquals(matchesExpected, matches);
//    }
//
//    @Test
//    void AddMatchSuccessScenario() {
//        // Arrange
//        IMatchRepository matchRepository = new MatchStubDatabase();
//        IMatchService matchService = new MatchService(matchRepository);
//
//        IMatchInfoRepository matchInfoRepository = new MatchInfoStubDatabase();
//        IMatchStatisticRepository matchStatisticRepository = new MatchStatisticStubDatabase();
//
//        // Act
//        matchService.add(new FriendlyMatch(3l, matchInfoRepository.get(3l), matchStatisticRepository.get(3l)));
//        List<Match> matches = matchService.getAll();
//
//        List<Match> matchesExpected = new ArrayList<>();
//        matchesExpected.add(new FriendlyMatch(1l, matchInfoRepository.get(1l), matchStatisticRepository.get(1l)));
//        matchesExpected.add(new FriendlyMatch(2l, matchInfoRepository.get(2l), matchStatisticRepository.get(2l)));
//        matchesExpected.add(new FriendlyMatch(3l, matchInfoRepository.get(3l), matchStatisticRepository.get(3l)));
//
//        // Assert
//        Assertions.assertEquals(matchesExpected, matches);
//    }
//
//    @Test
//    void UpdateMatchSuccessScenario(){
//        // Arrange
//        IMatchRepository matchRepository = new MatchStubDatabase();
//        IMatchService matchService = new MatchService(matchRepository);
//
//        IMatchInfoRepository matchInfoRepository = new MatchInfoStubDatabase();
//        IMatchStatisticRepository matchStatisticRepository = new MatchStatisticStubDatabase();
//
//        // Act
//        matchService.update(new FriendlyMatch(1l, matchInfoRepository.get(1l), matchStatisticRepository.get(2l)));
//        List<Match> matches = matchService.getAll();
//
//        List<Match> matchesExpected = new ArrayList<>();
//        matchesExpected.add(new FriendlyMatch(1l, matchInfoRepository.get(1l), matchStatisticRepository.get(1l)));
//        matchesExpected.add(new FriendlyMatch(2l, matchInfoRepository.get(2l), matchStatisticRepository.get(2l)));
//        matchesExpected.set(0, new FriendlyMatch(1l, matchInfoRepository.get(1l), matchStatisticRepository.get(2l)));
//
//        // Assert
//        Assertions.assertEquals(matchesExpected, matches);
//    }
//}
