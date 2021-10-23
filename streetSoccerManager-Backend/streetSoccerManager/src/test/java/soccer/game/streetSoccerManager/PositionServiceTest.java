package soccer.game.streetSoccerManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import soccer.game.streetSoccerManager.model.*;
import soccer.game.streetSoccerManager.repository.repositories.Position.PositionFakeDatabase;
import soccer.game.streetSoccerManager.repository.repositories.Team.TeamFakeDatabase;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPositionRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.ITeamRepository;
import soccer.game.streetSoccerManager.service.PositionService;
import soccer.game.streetSoccerManager.service.TeamService;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IPositionService;
import soccer.game.streetSoccerManager.service.serviceInterfaces.ITeamService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class PositionServiceTest {
    @Test
    void GetAllPositionsSuccessScenario() {
        // Arrange
        IPositionRepository positionRepository = new PositionFakeDatabase();
        IPositionService positionService = new PositionService(positionRepository);

        // Act
        List<Position> positions = positionService.getAll();
        positions = positions.stream().filter(pos -> pos.getId() < 2).collect(Collectors.toList());

        List<Position> positionsExpected = new ArrayList<>();
        positionsExpected.add(new Position(0l, "ATACK", "ST"));
        positionsExpected.add(new Position(1l, "ATACK", "LW"));

        // Assert
        Assertions.assertEquals(positionsExpected, positions);

    }

    @Test
    void GetPositionSuccessScenario() {
        // Arrange
        IPositionRepository positionRepository = new PositionFakeDatabase();
        IPositionService positionService = new PositionService(positionRepository);

        // Act
        Position position = positionService.get(0l);

        // Assert
        Assertions.assertEquals(new Position(0l, "ATACK", "ST"), position);
    }

    @Test
    void DeletePositionSuccessScenario(){
        // Arrange
        IPositionRepository positionRepository = new PositionFakeDatabase();
        IPositionService positionService = new PositionService(positionRepository);

        // Act
        positionService.delete(0l);
        List<Position> positions = positionService.getAll();
        positions = positions.stream().filter(pos -> pos.getId() < 2).collect(Collectors.toList());

        List<Position> positionsExpected = new ArrayList<>();
        positionsExpected.add(new Position(0l, "ATACK", "ST"));
        positionsExpected.add(new Position(1l, "ATACK", "LW"));
        positionsExpected.remove(0);

        // Assert
        Assertions.assertEquals(positionsExpected, positions);
    }

    @Test
    void AddPositionSuccessScenario() {
        // Arrange
        IPositionRepository positionRepository = new PositionFakeDatabase();
        IPositionService positionService = new PositionService(positionRepository);

        // Act
        positionService.add(new Position(15l, "SUB", "SUB6"));
        List<Position> positions = positionService.getAll();
        positions = positions.stream().filter(pos -> pos.getId() < 2 || pos.getId().equals(15l)).collect(Collectors.toList());

        List<Position> positionsExpected = new ArrayList<>();
        positionsExpected.add(new Position(0l, "ATACK", "ST"));
        positionsExpected.add(new Position(1l, "ATACK", "LW"));
        positionsExpected.add(new Position(15l, "SUB", "SUB6"));

        // Assert
        Assertions.assertEquals(positionsExpected, positions);
    }

    @Test
    void UpdatePositionSuccessScenario(){
        // Arrange
        IPositionRepository positionRepository = new PositionFakeDatabase();
        IPositionService positionService = new PositionService(positionRepository);

        // Act
        positionService.update(new Position(1l, "SUB", "SUB6"));
        List<Position> positions = positionService.getAll();
        positions = positions.stream().filter(pos -> pos.getId() < 2).collect(Collectors.toList());

        List<Position> positionsExpected = new ArrayList<>();
        positionsExpected.add(new Position(0l, "ATACK", "ST"));
        positionsExpected.add(new Position(1l, "ATACK", "LW"));
        positionsExpected.set(1, new Position(1l, "SUB", "SUB6"));

        // Assert
        Assertions.assertEquals(positionsExpected, positions);
    }
}
