package soccer.game.streetSoccerManager.service.serviceInterfaces;

import soccer.game.streetSoccerManager.model.entities.PlayerPersonalInfo;

import java.util.List;

public interface IPlayerPersonalInfoService {
    List<PlayerPersonalInfo> getAll();
    PlayerPersonalInfo get(Long id);
    Boolean delete(Long id);
    Boolean add(PlayerPersonalInfo playerPersonalInfo);
    Boolean update(PlayerPersonalInfo playerPersonalInfo);
}
