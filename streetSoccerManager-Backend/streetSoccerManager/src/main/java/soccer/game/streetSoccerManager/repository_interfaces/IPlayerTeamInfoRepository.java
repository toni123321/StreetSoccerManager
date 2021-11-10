package soccer.game.streetSoccerManager.repository_interfaces;

import soccer.game.streetSoccerManager.model.entities.PlayerTeamInfo;

import java.util.List;

public interface IPlayerTeamInfoRepository {
    List<PlayerTeamInfo> getAll();
    PlayerTeamInfo get(Long id);
    Boolean delete(Long id);
    Boolean add(PlayerTeamInfo playerTeamInfo);
    Boolean update(PlayerTeamInfo playerTeamInfo);
    void deleteAll();
}
