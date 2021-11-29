package soccer.game.streetsocceranager.repository_interfaces;

import soccer.game.streetsocceranager.model.entities.Match;

import java.util.List;

public interface IMatchRepository {
    List<Match> getAll();
    Match get(Long id);
    Boolean delete(Long id);
    Match add(Match match);
    Match update(Match match);
    void deleteAll();
}
