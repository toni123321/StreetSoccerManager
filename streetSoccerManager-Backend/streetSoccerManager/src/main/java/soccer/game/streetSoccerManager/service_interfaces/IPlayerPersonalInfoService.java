package soccer.game.streetSoccerManager.service_interfaces;

import soccer.game.streetSoccerManager.model.entities.PlayerPersonalInfo;

import java.util.List;

public interface IPlayerPersonalInfoService {
    List<PlayerPersonalInfo> getAll();
    PlayerPersonalInfo get(Long id);
    Boolean delete(Long id);
    PlayerPersonalInfo add(PlayerPersonalInfo playerPersonalInfo);
    PlayerPersonalInfo update(PlayerPersonalInfo playerPersonalInfo);
    void deleteAll();
}
