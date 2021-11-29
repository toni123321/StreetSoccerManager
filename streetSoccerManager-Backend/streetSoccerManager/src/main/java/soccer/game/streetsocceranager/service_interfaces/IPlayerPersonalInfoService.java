package soccer.game.streetsocceranager.service_interfaces;

import soccer.game.streetsocceranager.model.entities.PlayerPersonalInfo;

import java.util.List;

public interface IPlayerPersonalInfoService {
    List<PlayerPersonalInfo> getAll();
    PlayerPersonalInfo get(Long id);
    Boolean delete(Long id);
    PlayerPersonalInfo add(PlayerPersonalInfo playerPersonalInfo);
    PlayerPersonalInfo update(PlayerPersonalInfo playerPersonalInfo);
    void deleteAll();
}
