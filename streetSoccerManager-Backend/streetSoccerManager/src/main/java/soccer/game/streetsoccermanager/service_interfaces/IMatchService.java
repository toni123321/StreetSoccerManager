package soccer.game.streetsoccermanager.service_interfaces;

import soccer.game.streetsoccermanager.model.dtos.StartFriendlyMatchDTO;
import soccer.game.streetsoccermanager.model.entities.Match;

import java.util.List;

public interface IMatchService {
    List<Match> getAll();
    Match get(Long id);
    Boolean delete(Long id);
    Match add(Match match);
    Match update(Match match);
    Match playFriendlyMatch(StartFriendlyMatchDTO startMatchInfo);
}
