package soccer.game.streetSoccerManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import soccer.game.streetSoccerManager.model.entities.MatchInfo;
import soccer.game.streetSoccerManager.repository.repositories.MatchInfo.MatchInfoStubDatabase;
import soccer.game.streetSoccerManager.repository.repositories.Team.TeamStubDatabase;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IMatchInfoRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.ITeamRepository;
import soccer.game.streetSoccerManager.service.MatchInfoService;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IMatchInfoService;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MatchInfoServiceTest {
    @Test
    void GetAllMatchesInfoSuccessScenario() {
        // Arrange
        IMatchInfoRepository matchInfoRepository = new MatchInfoStubDatabase();
        IMatchInfoService matchInfoService = new MatchInfoService(matchInfoRepository);
        ITeamRepository teamRepository = new TeamStubDatabase();

        // Act
        List<MatchInfo> matchesInfo = matchInfoService.getAll();

        List<MatchInfo> matchesInfoExpected = new ArrayList<>();
        matchesInfoExpected.add(new MatchInfo(1l, teamRepository.get(0l), teamRepository.get(1l)));
        matchesInfoExpected.add(new MatchInfo(2l, teamRepository.get(0l), teamRepository.get(2l)));
        matchesInfoExpected.add(new MatchInfo(3l, teamRepository.get(0l), teamRepository.get(3l)));


        // Assert
        Assertions.assertEquals(matchesInfoExpected, matchesInfo);

    }

    @Test
    void GetMatchInfoSuccessScenario() {
        // Arrange
        IMatchInfoRepository matchInfoRepository = new MatchInfoStubDatabase();
        IMatchInfoService matchInfoService = new MatchInfoService(matchInfoRepository);
        ITeamRepository teamRepository = new TeamStubDatabase();

        // Act
        MatchInfo matchInfo = matchInfoService.get(1l);

        // Assert
        Assertions.assertEquals(new MatchInfo(1l, teamRepository.get(0l), teamRepository.get(1l)), matchInfo);
    }

    @Test
    void DeleteMatchInfoSuccessScenario(){
        // Arrange
        IMatchInfoRepository matchInfoRepository = new MatchInfoStubDatabase();
        IMatchInfoService matchInfoService = new MatchInfoService(matchInfoRepository);
        ITeamRepository teamRepository = new TeamStubDatabase();

        // Act
        matchInfoService.delete(1l);
        List<MatchInfo> matchesInfo = matchInfoService.getAll();

        List<MatchInfo> matchesInfoExpected = new ArrayList<>();
        matchesInfoExpected.add(new MatchInfo(1l, teamRepository.get(0l), teamRepository.get(1l)));
        matchesInfoExpected.add(new MatchInfo(2l, teamRepository.get(0l), teamRepository.get(2l)));
        matchesInfoExpected.add(new MatchInfo(3l, teamRepository.get(0l), teamRepository.get(3l)));
        matchesInfoExpected.remove(0);

        // Assert
        Assertions.assertEquals(matchesInfoExpected, matchesInfo);
    }

    @Test
    void AddMatchInfoSuccessScenario() {
        // Arrange
        IMatchInfoRepository matchInfoRepository = new MatchInfoStubDatabase();
        IMatchInfoService matchInfoService = new MatchInfoService(matchInfoRepository);
        ITeamRepository teamRepository = new TeamStubDatabase();

        // Act
        matchInfoService.add(new MatchInfo(4l, teamRepository.get(3l), teamRepository.get(0l)));
        List<MatchInfo> matchesInfo = matchInfoService.getAll();

        List<MatchInfo> matchesInfoExpected = new ArrayList<>();
        matchesInfoExpected.add(new MatchInfo(1l, teamRepository.get(0l), teamRepository.get(1l)));
        matchesInfoExpected.add(new MatchInfo(2l, teamRepository.get(0l), teamRepository.get(2l)));
        matchesInfoExpected.add(new MatchInfo(3l, teamRepository.get(0l), teamRepository.get(3l)));
        matchesInfoExpected.add(3, new MatchInfo(4l, teamRepository.get(3l), teamRepository.get(0l)));

        // Assert
        Assertions.assertEquals(matchesInfoExpected, matchesInfo);
    }

    @Test
    void UpdateMatchInfoSuccessScenario(){
        // Arrange
        IMatchInfoRepository matchInfoRepository = new MatchInfoStubDatabase();
        IMatchInfoService matchInfoService = new MatchInfoService(matchInfoRepository);
        ITeamRepository teamRepository = new TeamStubDatabase();

        // Act
        matchInfoService.update(new MatchInfo(3l, teamRepository.get(3l), teamRepository.get(0l)));
        List<MatchInfo> matchesInfo = matchInfoService.getAll();

        List<MatchInfo> matchesInfoExpected = new ArrayList<>();
        matchesInfoExpected.add(new MatchInfo(1l, teamRepository.get(0l), teamRepository.get(1l)));
        matchesInfoExpected.add(new MatchInfo(2l, teamRepository.get(0l), teamRepository.get(2l)));
        matchesInfoExpected.add(new MatchInfo(3l, teamRepository.get(0l), teamRepository.get(3l)));
        matchesInfoExpected.set(2, new MatchInfo(3l, teamRepository.get(3l), teamRepository.get(0l)));

        // Assert
        Assertions.assertEquals(matchesInfoExpected, matchesInfo);
    }
}
