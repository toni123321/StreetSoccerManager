package soccer.game.streetsoccermanager.repository_interfaces;

import soccer.game.streetsoccermanager.model.entities.PlayerTeamInfo;

import java.util.List;

public interface IPlayerTeamInfoRepository {
    List<PlayerTeamInfo> getAll();
    PlayerTeamInfo get(Long id);
    Boolean delete(Long id);
    PlayerTeamInfo add(PlayerTeamInfo playerTeamInfo);
    PlayerTeamInfo update(PlayerTeamInfo playerTeamInfo);
    void deleteAll();
}
