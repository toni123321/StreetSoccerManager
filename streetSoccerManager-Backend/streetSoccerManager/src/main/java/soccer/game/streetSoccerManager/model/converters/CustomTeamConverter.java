package soccer.game.streetSoccerManager.model.converters;

import soccer.game.streetSoccerManager.model.dtos.CustomTeamDTO;
import soccer.game.streetSoccerManager.model.dtos.PlayerDTO;
import soccer.game.streetSoccerManager.model.entities.CustomTeam;
import soccer.game.streetSoccerManager.model.entities.Player;

public class CustomTeamConverter {
    public CustomTeam convertCustomTeamDtoToCustomTeam(CustomTeamDTO customTeamDTO){
        return new CustomTeam(customTeamDTO.getId(), customTeamDTO.getName(), customTeamDTO.getFormation(), customTeamDTO.getManager());
    }
    public CustomTeamDTO convertCustomTeamToCustomTeamDto(CustomTeam customTeam) {
        return new CustomTeamDTO(customTeam.getId(), customTeam.getName(), customTeam.getFormation(), customTeam.getManager());
    }
}
