package soccer.game.streetSoccerManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import soccer.game.streetSoccerManager.model.entities.MatchStatistic;
import soccer.game.streetSoccerManager.repository.repositories.MatchStatistic.MatchStatisticStubDatabase;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IMatchStatisticRepository;
import soccer.game.streetSoccerManager.service.MatchStatisticService;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IMatchStatisticService;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MatchStatisticServiceTest {
    @Test
    void GetAllMatchesStatisticSuccessScenario() {
        // Arrange
        IMatchStatisticRepository matchStatisticRepository = new MatchStatisticStubDatabase();
        IMatchStatisticService matchStatisticService = new MatchStatisticService(matchStatisticRepository);

        // Act
        List<MatchStatistic> matchesStatistic = matchStatisticService.getAll();

        List<MatchStatistic> matchesStatisticExpected = new ArrayList<>();
        matchesStatisticExpected.add(new MatchStatistic(1l, 1, 1, "text"));
        matchesStatisticExpected.add(new MatchStatistic(2l, 1, 2, "text"));
        matchesStatisticExpected.add(new MatchStatistic(3l, 1, 3, "text"));


        // Assert
        Assertions.assertEquals(matchesStatisticExpected, matchesStatistic);

    }

    @Test
    void GetMatchStatisticSuccessScenario() {
        // Arrange
        IMatchStatisticRepository matchStatisticRepository = new MatchStatisticStubDatabase();
        IMatchStatisticService matchStatisticService = new MatchStatisticService(matchStatisticRepository);

        // Act
        MatchStatistic matchStatistic = matchStatisticService.get(1l);

        // Assert
        Assertions.assertEquals(new MatchStatistic(1l, 1, 1, "text"), matchStatistic);

    }

    @Test
    void DeleteMatchStatisticSuccessScenario(){
        // Arrange
        IMatchStatisticRepository matchStatisticRepository = new MatchStatisticStubDatabase();
        IMatchStatisticService matchStatisticService = new MatchStatisticService(matchStatisticRepository);

        // Act
        matchStatisticService.delete(1l);
        List<MatchStatistic> matchesStatistic = matchStatisticService.getAll();

        List<MatchStatistic> matchesStatisticExpected = new ArrayList<>();
        matchesStatisticExpected.add(new MatchStatistic(1l, 1, 1, "text"));
        matchesStatisticExpected.add(new MatchStatistic(2l, 1, 2, "text"));
        matchesStatisticExpected.add(new MatchStatistic(3l, 1, 3, "text"));

        matchesStatisticExpected.remove(0);

        // Assert
        Assertions.assertEquals(matchesStatisticExpected, matchesStatistic);
    }

    @Test
    void AddMatchStatisticSuccessScenario() {
        // Arrange
        IMatchStatisticRepository matchStatisticRepository = new MatchStatisticStubDatabase();
        IMatchStatisticService matchStatisticService = new MatchStatisticService(matchStatisticRepository);

        // Act
        matchStatisticService.add(new MatchStatistic(4l, 3, 4, "text"));
        List<MatchStatistic> matchesStatistic = matchStatisticService.getAll();

        List<MatchStatistic> matchesStatisticExpected = new ArrayList<>();
        matchesStatisticExpected.add(new MatchStatistic(1l, 1, 1, "text"));
        matchesStatisticExpected.add(new MatchStatistic(2l, 1, 2, "text"));
        matchesStatisticExpected.add(new MatchStatistic(3l, 1, 3, "text"));
        matchesStatisticExpected.add(new MatchStatistic(4l, 3, 4, "text"));

        // Assert
        Assertions.assertEquals(matchesStatisticExpected, matchesStatistic);

    }

    @Test
    void UpdateMatchStatisticSuccessScenario(){
        // Arrange
        IMatchStatisticRepository matchStatisticRepository = new MatchStatisticStubDatabase();
        IMatchStatisticService matchStatisticService = new MatchStatisticService(matchStatisticRepository);

        // Act
        matchStatisticService.update(new MatchStatistic(3l, 3, 4, "text"));
        List<MatchStatistic> matchesStatistic = matchStatisticService.getAll();

        List<MatchStatistic> matchesStatisticExpected = new ArrayList<>();
        matchesStatisticExpected.add(new MatchStatistic(1l, 1, 1, "text"));
        matchesStatisticExpected.add(new MatchStatistic(2l, 1, 2, "text"));
        matchesStatisticExpected.add(new MatchStatistic(3l, 1, 3, "text"));
        matchesStatisticExpected.set(2, new MatchStatistic(3l, 3, 4, "text"));

        // Assert
        Assertions.assertEquals(matchesStatisticExpected, matchesStatistic);
    }
}
