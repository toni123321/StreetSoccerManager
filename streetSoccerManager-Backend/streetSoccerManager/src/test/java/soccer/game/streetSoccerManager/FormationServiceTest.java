package soccer.game.streetSoccerManager;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import soccer.game.streetSoccerManager.model.entities.Formation;
import soccer.game.streetSoccerManager.repository.Formation.FormationStubDatabase;
import soccer.game.streetSoccerManager.repository_interfaces.IFormationRepository;
import soccer.game.streetSoccerManager.service.FormationService;
import soccer.game.streetSoccerManager.service_interfaces.IFormationService;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class FormationServiceTest {

    @Test
    void GetAllFormationsSuccessScenario() {
        // Arrange
        IFormationRepository formationRepository = new FormationStubDatabase();
        IFormationService formationService = new FormationService(formationRepository);

        // Act
        List<Formation> formations = formationService.getAll();

        List<Formation> formationsExpected = new ArrayList<>();
        formationsExpected.add(new Formation(1l, "1-2-1"));
        formationsExpected.add(new Formation(2l, "2-1-1"));

        // Assert
        Assertions.assertEquals(formationsExpected, formations);
    }

    @Test
    void GetFormationSuccessScenario() {
        // Arrange
        IFormationRepository formationRepository = new FormationStubDatabase();
        IFormationService formationService = new FormationService(formationRepository);

        // Act
        Formation formation = formationService.get(1l);

        // Assert
        Assertions.assertEquals(new Formation(1l, "1-2-1"), formation);
    }

    @Test
    void DeleteFormationSuccessScenario(){
        // Arrange
        IFormationRepository formationRepository = new FormationStubDatabase();
        IFormationService formationService = new FormationService(formationRepository);

        // Act
        formationService.delete(1l);
        List<Formation> formations = formationService.getAll();

        List<Formation> formationsExpected = new ArrayList<>();
        formationsExpected.add(new Formation(1l, "1-2-1"));
        formationsExpected.add(new Formation(2l, "2-1-1"));
        formationsExpected.remove(0);

        // Assert
        Assertions.assertEquals(formationsExpected, formations);
    }

    @Test
    void AddFormationSuccessScenario() {
        // Arrange
        IFormationRepository formationRepository = new FormationStubDatabase();
        IFormationService formationService = new FormationService(formationRepository);

        // Act
        formationService.add(new Formation(3l, "3-1-0"));
        List<Formation> formations = formationService.getAll();

        List<Formation> formationsExpected = new ArrayList<>();
        formationsExpected.add(new Formation(1l, "1-2-1"));
        formationsExpected.add(new Formation(2l, "2-1-1"));
        formationsExpected.add(new Formation(3l, "3-1-0"));

        // Assert
        Assertions.assertEquals(formationsExpected, formations);
    }

    @Test
    void UpdateTeamSuccessScenario(){
        // Arrange
        IFormationRepository formationRepository = new FormationStubDatabase();
        IFormationService formationService = new FormationService(formationRepository);

        // Act
        formationService.update(new Formation(2l, "3-1-0"));
        List<Formation> formations = formationService.getAll();

        List<Formation> formationsExpected = new ArrayList<>();
        formationsExpected.add(new Formation(1l, "1-2-1"));
        formationsExpected.add(new Formation(2l, "2-1-1"));
        formationsExpected.set(1, new Formation(2l, "3-1-0"));

        // Assert
        Assertions.assertEquals(formationsExpected, formations);
    }
}
