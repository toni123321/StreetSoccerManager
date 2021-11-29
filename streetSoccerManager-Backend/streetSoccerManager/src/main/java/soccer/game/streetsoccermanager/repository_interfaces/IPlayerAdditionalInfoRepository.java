package soccer.game.streetsoccermanager.repository_interfaces;

import soccer.game.streetsoccermanager.model.entities.PlayerAdditionalInfo;

import java.util.List;

public interface IPlayerAdditionalInfoRepository {
    List<PlayerAdditionalInfo> getAll();
    PlayerAdditionalInfo get(Long id);
    Boolean delete(Long id);
    PlayerAdditionalInfo add(PlayerAdditionalInfo playerAdditionalInfo);
    PlayerAdditionalInfo update(PlayerAdditionalInfo playerAdditionalInfo);
    void deleteAll();
}
