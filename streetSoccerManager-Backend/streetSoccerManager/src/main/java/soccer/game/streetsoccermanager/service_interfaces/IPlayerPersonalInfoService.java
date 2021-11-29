package soccer.game.streetsoccermanager.service_interfaces;

import soccer.game.streetsoccermanager.model.entities.PlayerPersonalInfo;

import java.util.List;

public interface IPlayerPersonalInfoService {
    List<PlayerPersonalInfo> getAll();
    PlayerPersonalInfo get(Long id);
    Boolean delete(Long id);
    PlayerPersonalInfo add(PlayerPersonalInfo playerPersonalInfo);
    PlayerPersonalInfo update(PlayerPersonalInfo playerPersonalInfo);
    void deleteAll();
}
