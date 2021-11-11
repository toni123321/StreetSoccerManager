package soccer.game.streetSoccerManager.service_interfaces;

import soccer.game.streetSoccerManager.model.dtos.PlayerPositionInfoDTO;
import soccer.game.streetSoccerManager.model.entities.PlayerPositionInfo;

import java.util.List;

public interface IPlayerPositionInfoService {
    List<PlayerPositionInfoDTO> getAll();
    PlayerPositionInfoDTO get(Long id);
    Boolean delete(Long id);
    PlayerPositionInfoDTO add(PlayerPositionInfoDTO playerPositionInfo);
    PlayerPositionInfoDTO update(PlayerPositionInfoDTO playerPositionInfo);
}
