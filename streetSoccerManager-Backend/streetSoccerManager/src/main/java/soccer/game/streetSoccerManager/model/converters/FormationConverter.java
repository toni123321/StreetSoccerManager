package soccer.game.streetSoccerManager.model.converters;

import soccer.game.streetSoccerManager.model.dtos.FormationDTO;
import soccer.game.streetSoccerManager.model.dtos.PlayerDTO;
import soccer.game.streetSoccerManager.model.entities.Formation;
import soccer.game.streetSoccerManager.model.entities.Player;

public class FormationConverter {
    public Formation convertFormationDtoToFormation(FormationDTO formationDTO){
        return new Formation(formationDTO.getId(), formationDTO.getName());
    }
    public FormationDTO convertFormationToFormationDto(Formation formation) {
        return new FormationDTO(formation.getId(), formation.getName());
    }
}
