package soccer.game.streetsocceranager.service_interfaces;

import soccer.game.streetsocceranager.model.dtos.StartFriendlyMatchDTO;
import soccer.game.streetsocceranager.model.entities.Match;

import java.util.List;

public interface IMatchService {
    List<Match> getAll();
    Match get(Long id);
    Boolean delete(Long id);
    Match add(Match match);
    Match update(Match match);
    Match playFriendlyMatch(StartFriendlyMatchDTO startMatchInfo);
}
