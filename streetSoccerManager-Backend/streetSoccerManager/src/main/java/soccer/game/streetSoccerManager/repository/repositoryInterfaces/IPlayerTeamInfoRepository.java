package soccer.game.streetSoccerManager.repository.repositoryInterfaces;

import soccer.game.streetSoccerManager.model.entities.PlayerTeamInfo;
import soccer.game.streetSoccerManager.model.entities.Position;

import java.util.List;

public interface IPlayerTeamInfoRepository {
    List<PlayerTeamInfo> getAll();
    PlayerTeamInfo get(Long id);
    Boolean delete(Long id);
    Boolean add(PlayerTeamInfo playerTeamInfo);
    Boolean update(PlayerTeamInfo playerTeamInfo);
}
