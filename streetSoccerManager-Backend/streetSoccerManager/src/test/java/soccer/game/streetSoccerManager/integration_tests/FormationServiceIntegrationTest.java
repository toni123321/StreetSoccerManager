package soccer.game.streetSoccerManager.integration_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import soccer.game.streetSoccerManager.model.entities.Formation;
import soccer.game.streetSoccerManager.service.FormationService;

import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
class FormationServiceIntegrationTest {

    //Arrange
    @Autowired
    FormationService formationService;
    List<Formation> formations = new ArrayList<>();
    List<Formation> formationsExpected = new ArrayList<>();

    @BeforeEach
    void clearDB() {
        // Clear
        formationService.deleteAll();
        formationsExpected.clear();

        // Add
        formationService.add(new Formation("1-2-1"));
        formationService.add(new Formation("2-1-1"));
        formations = formationService.getAll();
        formationsExpected.add(new Formation(formations.get(0).getId(),  "1-2-1"));
        formationsExpected.add(new Formation(formations.get(1).getId(),  "2-1-1"));
    }

    @Test
    void getAllFormationsSuccessScenario() {
        // Assert
        Assertions.assertEquals(formationsExpected, formationService.getAll()) ;
    }


    @Test
    void GetFormationSuccessScenario() {
        // Act
        Formation formation = formationService.get(formations.get(0).getId());

        // Assert
        Assertions.assertEquals(new Formation(formations.get(0).getId(), "1-2-1"), formation);
    }

    @Test
    void DeleteFormationSuccessScenario(){
        // Act
        formationService.delete(formations.get(0).getId());
        formationsExpected.remove(0);

        // Assert
        Assertions.assertEquals(formationsExpected, formationService.getAll());
    }

    @Test
    void AddFormationSuccessScenario() {
        // Act
        formationService.add(new Formation("3-1-0"));
        formations = formationService.getAll();
        Long lastIndexId = formations.get(formations.size() - 1).getId();
        formationsExpected.add(new Formation(lastIndexId, "3-1-0"));

        // Assert
        Assertions.assertEquals(formationsExpected, formationService.getAll());
    }

    @Test
    void UpdateTeamSuccessScenario(){
        // Act
        formationService.update(new Formation(formations.get(0).getId(), "3-1-0"));
        formationsExpected.set(0, new Formation(formations.get(0).getId(), "3-1-0"));

        // Assert
        Assertions.assertEquals(formationsExpected, formationService.getAll());
    }
}
