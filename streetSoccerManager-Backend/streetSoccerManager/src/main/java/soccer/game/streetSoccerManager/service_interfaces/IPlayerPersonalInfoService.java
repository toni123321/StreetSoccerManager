package soccer.game.streetSoccerManager.service_interfaces;

import soccer.game.streetSoccerManager.model.dtos.PlayerPersonalInfoDTO;
import soccer.game.streetSoccerManager.model.entities.PlayerPersonalInfo;

import java.util.List;

public interface IPlayerPersonalInfoService {
    List<PlayerPersonalInfoDTO> getAll();
    PlayerPersonalInfoDTO get(Long id);
    Boolean delete(Long id);
    PlayerPersonalInfoDTO add(PlayerPersonalInfoDTO playerPersonalInfo);
    PlayerPersonalInfoDTO update(PlayerPersonalInfoDTO playerPersonalInfo);
}
