package soccer.game.streetSoccerManager.service_interfaces;



import soccer.game.streetSoccerManager.model.dtos.FormationDTO;
import soccer.game.streetSoccerManager.model.entities.Formation;

import java.util.List;

public interface IFormationService {
    List<FormationDTO> getAll();
    FormationDTO get(Long id);
    Boolean delete(Long id);
    FormationDTO add(FormationDTO formation);
    FormationDTO update(FormationDTO formation);
    void deleteAll();
}

