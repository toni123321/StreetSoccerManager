package soccer.game.streetSoccerManager.model.converters;

import soccer.game.streetSoccerManager.model.dtos.PlayerDTO;
import soccer.game.streetSoccerManager.model.dtos.PlayerPositionInfoDTO;
import soccer.game.streetSoccerManager.model.entities.Player;
import soccer.game.streetSoccerManager.model.entities.PlayerPositionInfo;

public class PlayerPositionInfoConverter {
    public PlayerPositionInfo convertPlayerPositionInfoDtoToPlayerPositionInfo(PlayerPositionInfoDTO playerPositionInfoDTO){
        return new PlayerPositionInfo(playerPositionInfoDTO.getId(), playerPositionInfoDTO.getPositionIndex(), playerPositionInfoDTO.getDefaultPosition(), playerPositionInfoDTO.getCurrentPosition(), playerPositionInfoDTO.isStarting());
    }
    public PlayerPositionInfoDTO convertPlayerPositionInfoToPlayerPositionInfoDto(PlayerPositionInfo playerPositionInfo) {
        return new PlayerPositionInfoDTO(playerPositionInfo.getId(), playerPositionInfo.getPositionIndex(), playerPositionInfo.getDefaultPosition(), playerPositionInfo.getCurrentPosition(), playerPositionInfo.isStarting());
    }
}
