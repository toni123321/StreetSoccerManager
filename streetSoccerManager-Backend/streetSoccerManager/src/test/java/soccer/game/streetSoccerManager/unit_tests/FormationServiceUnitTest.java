package soccer.game.streetSoccerManager.unit_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import soccer.game.streetSoccerManager.model.dtos.FormationDTO;
import soccer.game.streetSoccerManager.model.entities.Formation;
import soccer.game.streetSoccerManager.repository_interfaces.IFormationRepository;
import soccer.game.streetSoccerManager.service.FormationService;
import soccer.game.streetSoccerManager.service_interfaces.IFormationService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
class FormationServiceUnitTest {

    @Mock
    IFormationRepository formationRepository;
    IFormationService formationService;

    @BeforeEach
    public void setUp()  {
        List<Formation> formations = List.of(
                new Formation(1l, "1-2-1"),
                new Formation(2l, "2-1-1")
        );
        when(formationRepository.getAll()).thenReturn(formations);
        when(formationRepository.get(1l)).thenReturn(formations.get(0));
        when(formationRepository.get(2l)).thenReturn(formations.get(1));
        when(formationRepository.add(new Formation("3-1-0"))).thenReturn(new Formation(3l, "3-1-0"));
        when(formationRepository.update(new Formation(2l, "3-1-0"))).thenReturn(new Formation(2l, "3-1-0"));
        formationService = new FormationService(formationRepository);
    }

    @Test
    void GetAllFormationsSuccessScenario() {
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
        // Act
        Formation formation = formationService.get(1l);

        // Assert
        Assertions.assertEquals(new Formation(1l, "1-2-1"), formation);
    }

    @Test
    void DeleteFormationSuccessScenario(){
        // Act
        formationService.delete(1l);
        verify(formationRepository).delete(1l);
        when(formationRepository.delete(2l)).thenReturn(true);
        Assertions.assertEquals(true, formationService.delete(2l));
        when(formationRepository.get(3l)).thenReturn(null);
        when(formationRepository.delete(3l)).thenReturn(false);
        Assertions.assertEquals(false, formationService.delete(3l));
    }

    @Test
    void AddFormationSuccessScenario() {
        // Act
        Formation newFormation = formationService.add(new Formation("3-1-0"));
        // Assert
        Assertions.assertEquals(new Formation(3l, "3-1-0"), newFormation);
    }

    @Test
    void UpdateTeamSuccessScenario(){
        // Act
        Formation updatedFormation = formationService.update(new Formation(2l, "3-1-0"));
        // Assert
        Assertions.assertEquals(new Formation(2l, "3-1-0"), updatedFormation);
    }


}
