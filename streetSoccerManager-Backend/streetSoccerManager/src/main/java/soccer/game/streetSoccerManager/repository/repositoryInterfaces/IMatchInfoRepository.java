package soccer.game.streetSoccerManager.repository.repositoryInterfaces;

import soccer.game.streetSoccerManager.model.entities.Formation;
import soccer.game.streetSoccerManager.model.entities.MatchInfo;

import java.util.List;

public interface IMatchInfoRepository {
    List<MatchInfo> getAll();
    MatchInfo get(Long id);
    Boolean delete(Long id);
    Boolean add(MatchInfo matchInfo);
    Boolean update(MatchInfo matchInfo);
}
