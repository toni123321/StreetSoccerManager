package soccer.game.streetSoccerManager.repository.repositoryInterfaces;

import soccer.game.streetSoccerManager.model.entities.PlayerPersonalInfo;
import soccer.game.streetSoccerManager.model.entities.Position;

import java.util.List;

public interface IPlayerPersonalInfoRepository {
    List<PlayerPersonalInfo> getAll();
    PlayerPersonalInfo get(Long id);
    Boolean delete(Long id);
    Boolean add(PlayerPersonalInfo playerPersonalInfo);
    Boolean update(PlayerPersonalInfo playerPersonalInfo);
}
