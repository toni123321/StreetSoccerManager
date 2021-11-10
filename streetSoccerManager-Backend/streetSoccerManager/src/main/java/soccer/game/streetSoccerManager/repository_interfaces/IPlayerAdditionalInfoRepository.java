package soccer.game.streetSoccerManager.repository_interfaces;

import soccer.game.streetSoccerManager.model.entities.PlayerAdditionalInfo;

import java.util.List;

public interface IPlayerAdditionalInfoRepository {
    List<PlayerAdditionalInfo> getAll();
    PlayerAdditionalInfo get(Long id);
    Boolean delete(Long id);
    Boolean add(PlayerAdditionalInfo playerAdditionalInfo);
    Boolean update(PlayerAdditionalInfo playerAdditionalInfo);
    void deleteAll();
}
