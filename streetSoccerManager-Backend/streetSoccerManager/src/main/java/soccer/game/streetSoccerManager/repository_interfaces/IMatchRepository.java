package soccer.game.streetSoccerManager.repository_interfaces;

import soccer.game.streetSoccerManager.model.entities.Match;

import java.util.List;

public interface IMatchRepository {
    List<Match> getAll();
    Match get(Long id);
    Boolean delete(Long id);
    Boolean add(Match match);
    Boolean update(Match match);
    void deleteAll();
}
