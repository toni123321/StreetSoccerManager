package soccer.game.streetSoccerManager.integration_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import soccer.game.streetSoccerManager.model.entities.Formation;
import soccer.game.streetSoccerManager.model.entities.Position;
import soccer.game.streetSoccerManager.service.FormationService;
import soccer.game.streetSoccerManager.service.PositionService;

import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
class PositionServiceIntegrationTest {
    //Arrange
    @Autowired
    PositionService positionService;
    List<Position> positions = new ArrayList<>();
    List<Position> positionsExpected = new ArrayList<>();

    @BeforeEach
    void clearDB() {
        // Clear
        positionService.deleteAll();
        positionsExpected.clear();

        // Add
        positionService.add(new Position("ATACK", "ST"));
        positionService.add(new Position("ATACK", "LW"));
        positions = positionService.getAll();
        positionsExpected.add(new Position(positions.get(0).getId(),  "ATACK", "ST"));
        positionsExpected.add(new Position(positions.get(1).getId(),  "ATACK", "LW"));
    }

    @Test
    void getAllFormationsSuccessScenario() {
        // Assert
        Assertions.assertEquals(positionsExpected, positionService.getAll()) ;
    }


    @Test
    void GetFormationSuccessScenario() {
        // Act
        Position position = positionService.get(positions.get(0).getId());

        // Assert
        Assertions.assertEquals(new Position(positions.get(0).getId(), "ATACK", "ST"), position);
    }

    @Test
    void DeleteFormationSuccessScenario(){
        // Act
        positionService.delete(positions.get(0).getId());
        positionsExpected.remove(0);

        // Assert
        Assertions.assertEquals(positionsExpected, positionService.getAll());
    }

    @Test
    void AddFormationSuccessScenario() {
        // Act
        positionService.add(new Position("ATACK", "RW"));
        positions = positionService.getAll();
        Long lastIndexId = positions.get(positions.size() - 1).getId();
        positionsExpected.add(new Position(lastIndexId, "ATACK", "RW"));

        // Assert
        Assertions.assertEquals(positionsExpected, positionService.getAll());
    }

    @Test
    void UpdateTeamSuccessScenario(){
        // Act
        positionService.update(new Position(positions.get(0).getId(), "DEF", "CB"));
        positionsExpected.set(0, new Position(positions.get(0).getId(), "DEF", "CB"));

        // Assert
        Assertions.assertEquals(positionsExpected, positionService.getAll());
    }
}
