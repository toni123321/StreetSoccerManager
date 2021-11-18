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
public class FormationServiceUnitTest {

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
        when(formationRepository.add(new Formation("3-1-0"))).thenReturn(new Formation(3l, "3-1-0"));
        when(formationRepository.update(new Formation(2l, "3-1-0"))).thenReturn(new Formation(2l, "3-1-0"));
        formationService = new FormationService(formationRepository);

    }

    @Test
    void GetAllFormationsSuccessScenario() {
        // Act
        List<FormationDTO> formations = formationService.getAll();

        List<FormationDTO> formationsExpected = new ArrayList<>();
        formationsExpected.add(new FormationDTO(1l, "1-2-1"));
        formationsExpected.add(new FormationDTO(2l, "2-1-1"));

        // Assert
        Assertions.assertEquals(formationsExpected, formations);
    }

    @Test
    void GetFormationSuccessScenario() {

        // Act
        FormationDTO formation = formationService.get(1l);

        // Assert
        Assertions.assertEquals(new FormationDTO(1l, "1-2-1"), formation);
    }

    @Test
    void DeleteFormationSuccessScenario(){
        // Act
        formationService.delete(1l);
        verify(formationRepository).delete(1l);
        when(formationRepository.delete(2l)).thenReturn(true);
        Assertions.assertEquals(true, formationService.delete(2l));
    }

    @Test
    void AddFormationSuccessScenario() {
        // Act
        FormationDTO newFormation = formationService.add(new FormationDTO("3-1-0"));
        // Assert
        Assertions.assertEquals(new FormationDTO(3l, "3-1-0"), newFormation);
    }

    @Test
    void UpdateTeamSuccessScenario(){
        // Act
        FormationDTO updatedFormation = formationService.update(new FormationDTO(2l, "3-1-0"));
        // Assert
        Assertions.assertEquals(new FormationDTO(2l, "3-1-0"), updatedFormation);
    }


}
