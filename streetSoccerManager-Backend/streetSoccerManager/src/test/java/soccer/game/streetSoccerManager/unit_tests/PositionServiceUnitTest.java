package soccer.game.streetSoccerManager.unit_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import soccer.game.streetSoccerManager.model.entities.Position;
import soccer.game.streetSoccerManager.repository_interfaces.IPositionRepository;
import soccer.game.streetSoccerManager.service.PositionService;
import soccer.game.streetSoccerManager.service_interfaces.IPositionService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
public class PositionServiceUnitTest {

    @Mock
    IPositionRepository positionRepository;
    IPositionService positionService;

    @BeforeEach
    public void setUp()  {
        List<Position> positions = List.of(
                new Position("ATACK", "ST"),
                new Position("DEF", "CB")
        );
        when(positionRepository.getAll()).thenReturn(positions);
        when(positionRepository.get(1l)).thenReturn(positions.get(0));
        when(positionRepository.get(2l)).thenReturn(positions.get(1));
        when(positionRepository.add(new Position("MID", "CM"))).thenReturn(new Position("MID", "CM"));
        when(positionRepository.update(new Position(2l, "DEF", "LB"))).thenReturn(new Position(2l, "DEF", "LB"));
        positionService = new PositionService(positionRepository);
    }

    @Test
    void GetAllPositionsSuccessScenario() {
        // Act
        List<Position> positions = positionService.getAll();

        List<Position> positionsExpected = new ArrayList<>();
        positionsExpected.add(new Position(1l, "ATACK", "ST"));
        positionsExpected.add(new Position(2l, "DEF", "CB"));

        // Assert
        Assertions.assertEquals(positionsExpected, positions);
    }

    @Test
    void GetPositionSuccessScenario() {
        // Act
        Position position = positionService.get(1l);

        // Assert
        Assertions.assertEquals(new Position(1l, "ATACK", "ST"), position);
    }

    @Test
    void DeletePositionSuccessScenario(){
        // Act
        positionService.delete(1l);
        verify(positionRepository).delete(1l);
        when(positionRepository.delete(2l)).thenReturn(true);
        Assertions.assertEquals(true, positionService.delete(2l));
        when(positionRepository.get(3l)).thenReturn(null);
        when(positionRepository.delete(3l)).thenReturn(false);
        Assertions.assertEquals(false, positionService.delete(3l));
    }

    @Test
    void AddPositionSuccessScenario() {
        // Act
        Position newPosition = positionService.add(new Position("MID", "CM"));
        // Assert
        Assertions.assertEquals(new Position(3l, "MID", "CM"), newPosition);
        Assertions.assertEquals(null, positionService.add(new Position(3l, "MID", "CM")));
    }

    @Test
    void UpdatePositionSuccessScenario(){
        // Act
        Position updatedPosition = positionService.update(new Position(2l, "DEF", "LB"));
        // Assert
        Assertions.assertEquals(new Position(2l, "DEF", "LB"), updatedPosition);
        Assertions.assertEquals(null, positionService.update(new Position("DEF", "LB")));
    }
}
