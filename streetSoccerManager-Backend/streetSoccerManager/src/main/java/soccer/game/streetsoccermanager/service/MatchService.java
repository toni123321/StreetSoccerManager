package soccer.game.streetsoccermanager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetsoccermanager.model.entities.Match;
import soccer.game.streetsoccermanager.repository_interfaces.IMatchRepository;
import soccer.game.streetsoccermanager.service_interfaces.IMatchService;

import java.util.List;

@Service
public class MatchService implements IMatchService {
    private IMatchRepository dataStore;

    public MatchService(@Qualifier("matchJPADatabase") IMatchRepository dataStore) {
        this.dataStore = dataStore;
    }


    @Override
    public List<Match> getAll() {
        return dataStore.getAll();
    }


    @Override
    public Match get(Long id) {
        return dataStore.get(id);
    }

    @Override
    public Boolean delete(Long id) {
        return dataStore.delete(id);
    }

    @Override
    public Match add(Match match) {
        match.setResult("0:0");
        match.setStatistic("Match started");
        return dataStore.add(match);
    }

    @Override
    public Match update(Match match) {
        return dataStore.update(match);
    }

    @Override
    public Match playFriendlyMatch(Long matchId, String command) {
        Match match = get(matchId);
        try {
            return this.update(PlayMatchManager.playFriendlyMatch(match, command));
        }
        catch (Exception e){
            e.getMessage();
            return match;
        }
    }

}
