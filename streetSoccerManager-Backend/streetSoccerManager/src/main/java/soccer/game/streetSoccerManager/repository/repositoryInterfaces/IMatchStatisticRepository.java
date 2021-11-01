package soccer.game.streetSoccerManager.repository.repositoryInterfaces;

import soccer.game.streetSoccerManager.model.entities.MatchStatistic;
import soccer.game.streetSoccerManager.model.entities.Position;

import java.util.List;

public interface IMatchStatisticRepository {
    List<MatchStatistic> getAll();
    MatchStatistic get(Long id);
    Boolean delete(Long id);
    Boolean add(MatchStatistic matchStatistic);
    Boolean update(MatchStatistic matchStatistic);
}
