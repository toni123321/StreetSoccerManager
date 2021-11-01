package soccer.game.streetSoccerManager.service.serviceInterfaces;

import soccer.game.streetSoccerManager.model.entities.PlayerTeamInfo;

import java.util.List;

public interface IPlayerTeamInfoService {
    List<PlayerTeamInfo> getAll();
    PlayerTeamInfo get(Long id);
    Boolean delete(Long id);
    Boolean add(PlayerTeamInfo playerTeamInfo);
    Boolean update(PlayerTeamInfo playerTeamInfo);
}
