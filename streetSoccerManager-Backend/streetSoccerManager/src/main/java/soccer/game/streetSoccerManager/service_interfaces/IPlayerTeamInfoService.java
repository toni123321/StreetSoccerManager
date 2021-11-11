package soccer.game.streetSoccerManager.service_interfaces;

import soccer.game.streetSoccerManager.model.dtos.PlayerTeamInfoDTO;
import soccer.game.streetSoccerManager.model.entities.PlayerTeamInfo;

import java.util.List;

public interface IPlayerTeamInfoService {
    List<PlayerTeamInfoDTO> getAll();
    PlayerTeamInfoDTO get(Long id);
    Boolean delete(Long id);
    PlayerTeamInfoDTO add(PlayerTeamInfoDTO playerTeamInfo);
    PlayerTeamInfoDTO update(PlayerTeamInfoDTO playerTeamInfo);
}
