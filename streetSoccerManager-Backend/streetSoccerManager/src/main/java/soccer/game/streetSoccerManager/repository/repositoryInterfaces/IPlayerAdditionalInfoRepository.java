package soccer.game.streetSoccerManager.repository.repositoryInterfaces;

import soccer.game.streetSoccerManager.model.entities.PlayerAdditionalInfo;
import soccer.game.streetSoccerManager.model.entities.Position;

import java.util.List;

public interface IPlayerAdditionalInfoRepository {
    List<PlayerAdditionalInfo> getAll();
    PlayerAdditionalInfo get(Long id);
    Boolean delete(Long id);
    Boolean add(PlayerAdditionalInfo playerAdditionalInfo);
    Boolean update(PlayerAdditionalInfo playerAdditionalInfo);
}
