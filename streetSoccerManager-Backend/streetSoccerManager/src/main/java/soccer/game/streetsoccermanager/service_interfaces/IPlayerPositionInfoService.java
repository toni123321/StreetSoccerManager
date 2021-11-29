package soccer.game.streetsoccermanager.service_interfaces;

import soccer.game.streetsoccermanager.model.entities.PlayerPositionInfo;

import java.util.List;

public interface IPlayerPositionInfoService {
    List<PlayerPositionInfo> getAll();
    PlayerPositionInfo get(Long id);
    Boolean delete(Long id);
    PlayerPositionInfo add(PlayerPositionInfo playerPositionInfo);
    PlayerPositionInfo update(PlayerPositionInfo playerPositionInfo);
    void deleteAll();
}
