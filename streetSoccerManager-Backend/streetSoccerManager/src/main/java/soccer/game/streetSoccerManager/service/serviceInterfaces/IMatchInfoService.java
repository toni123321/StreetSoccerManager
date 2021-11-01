package soccer.game.streetSoccerManager.service.serviceInterfaces;

import soccer.game.streetSoccerManager.model.entities.MatchInfo;

import java.util.List;

public interface IMatchInfoService {
    List<MatchInfo> getAll();
    MatchInfo get(Long id);
    Boolean delete(Long id);
    Boolean add(MatchInfo matchInfo);
    Boolean update(MatchInfo matchInfo);
}
