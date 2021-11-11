package soccer.game.streetSoccerManager.service_interfaces;

import soccer.game.streetSoccerManager.model.dtos.PlayerAdditionalInfoDTO;
import soccer.game.streetSoccerManager.model.entities.PlayerAdditionalInfo;

import java.util.List;

public interface IPlayerAdditionalInfoService {
    List<PlayerAdditionalInfoDTO> getAll();
    PlayerAdditionalInfoDTO get(Long id);
    Boolean delete(Long id);
    PlayerAdditionalInfoDTO add(PlayerAdditionalInfoDTO playerAdditionalInfo);
    PlayerAdditionalInfoDTO update(PlayerAdditionalInfoDTO playerAdditionalInfo);
}
