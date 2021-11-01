package soccer.game.streetSoccerManager.service.serviceInterfaces;

import soccer.game.streetSoccerManager.model.entities.Match;

import java.util.List;

public interface IMatchService {
    List<Match> getAll();
    Match get(Long id);
    Boolean delete(Long id);
    Boolean add(Match match);
    Boolean update(Match match);
}
