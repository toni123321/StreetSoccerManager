package soccer.game.streetSoccerManager.model.converters;

import soccer.game.streetSoccerManager.model.dtos.PlayerDTO;
import soccer.game.streetSoccerManager.model.entities.Player;

public class PlayerConverter {
    public Player convertPlayerDtoToPlayer(PlayerDTO playerDTO){
        return new Player(playerDTO.getId(), playerDTO.getPlayerPersonalInfo(), playerDTO.getPlayerPositionInfo(), playerDTO.getPlayerTeamInfo(), playerDTO.getPlayerAdditionalInfo());
    }
    public PlayerDTO convertPlayerToPlayerDto(Player player) {
        return new PlayerDTO(player.getId(), player.getPlayerPersonalInfo(), player.getPlayerPositionInfo(), player.getPlayerTeamInfo(), player.getPlayerAdditionalInfo());
    }


}
