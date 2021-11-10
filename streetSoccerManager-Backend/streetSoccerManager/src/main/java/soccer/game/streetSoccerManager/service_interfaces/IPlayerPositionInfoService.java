package soccer.game.streetSoccerManager.service_interfaces;

import soccer.game.streetSoccerManager.model.entities.PlayerPositionInfo;

import java.util.List;

public interface IPlayerPositionInfoService {
    List<PlayerPositionInfo> getAll();
    PlayerPositionInfo get(Long id);
    Boolean delete(Long id);
    Boolean add(PlayerPositionInfo playerPositionInfo);
    Boolean update(PlayerPositionInfo playerPositionInfo);
}
