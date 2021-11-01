package soccer.game.streetSoccerManager.service.serviceInterfaces;

import soccer.game.streetSoccerManager.model.entities.MatchStatistic;

import java.util.List;

public interface IMatchStatisticService {
    List<MatchStatistic> getAll();
    MatchStatistic get(Long id);
    Boolean delete(Long id);
    Boolean add(MatchStatistic matchStatistic);
    Boolean update(MatchStatistic matchStatistic);
}
