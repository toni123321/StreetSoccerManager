package soccer.game.streetsocceranager.repository_interfaces;



import soccer.game.streetsocceranager.model.entities.PlayerPositionInfo;

import java.util.List;

public interface IPlayerPositionInfoRepository {
    List<PlayerPositionInfo> getAll();
    PlayerPositionInfo get(Long id);
    Boolean delete(Long id);
    PlayerPositionInfo add(PlayerPositionInfo playerPositionInfo);
    PlayerPositionInfo update(PlayerPositionInfo playerPositionInfo);
    void deleteAll();
}
