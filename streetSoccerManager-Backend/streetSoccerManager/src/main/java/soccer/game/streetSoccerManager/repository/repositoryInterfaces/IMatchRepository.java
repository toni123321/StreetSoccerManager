package soccer.game.streetSoccerManager.repository.repositoryInterfaces;

import soccer.game.streetSoccerManager.model.entities.Match;
import soccer.game.streetSoccerManager.model.entities.Position;

import java.util.List;

public interface IMatchRepository {
    List<Match> getAll();
    Match get(Long id);
    Boolean delete(Long id);
    Boolean add(Match match);
    Boolean update(Match match);
}
