package soccer.game.streetSoccerManager.model.converters;

import soccer.game.streetSoccerManager.model.dtos.EndUserDTO;
import soccer.game.streetSoccerManager.model.dtos.PlayerDTO;
import soccer.game.streetSoccerManager.model.entities.EndUser;
import soccer.game.streetSoccerManager.model.entities.Player;

public class EndUserConverter {
    public EndUser convertEndUserDtoToEndUser(EndUserDTO endUserDTO){
        return new EndUser(endUserDTO.getId(), endUserDTO.getEmail(), endUserDTO.getPassword(), endUserDTO.getNickname(), endUserDTO.getPoints());
    }
    public EndUserDTO convertEndUserToEndUserDto(EndUser endUser) {
        return new EndUserDTO(endUser.getId(), endUser.getEmail(), endUser.getPassword(), endUser.getNickname(), endUser.getPoints());
    }
}
