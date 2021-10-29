package soccer.game.streetSoccerManager.repository.repositoryInterfaces;



import soccer.game.streetSoccerManager.model.entities.PlayerPositionInfo;

import java.util.List;

public interface IPlayerPositionInfoRepository {
    List<PlayerPositionInfo> getAll();
    PlayerPositionInfo get(Long id);
    Boolean delete(Long id);
    Boolean add(PlayerPositionInfo playerPositionInfo);
    Boolean update(PlayerPositionInfo playerPositionInfo);
}
