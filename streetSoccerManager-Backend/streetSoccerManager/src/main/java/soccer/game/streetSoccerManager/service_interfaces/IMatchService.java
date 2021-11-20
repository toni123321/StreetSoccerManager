package soccer.game.streetSoccerManager.service_interfaces;

import soccer.game.streetSoccerManager.model.dtos.StartFriendlyMatchDTO;
import soccer.game.streetSoccerManager.model.entities.Match;

import java.util.List;

public interface IMatchService {
    List<Match> getAll();
    Match get(Long id);
    Boolean delete(Long id);
    Match add(Match match);
    Match update(Match match);
    Match playFriendlyMatch(StartFriendlyMatchDTO startMatchInfo);
}
