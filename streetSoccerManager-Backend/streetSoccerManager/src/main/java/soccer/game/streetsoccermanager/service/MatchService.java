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
        if(get(id) != null) {
            dataStore.delete(id);
            return true;
        }
        return false;
    }

    @Override
    public Match add(Match match) {
        if(match.getId() == null) {
            match.setResult("0:0");
            match.setStatistic("Match started");
            return dataStore.add(match);
        }
        return null;
    }

    @Override
    public Match update(Match match) {
        if(match.getId() != null) {
            return dataStore.update(match);
        }
        return null;
    }

    @Override
    public Match playFriendlyMatch(Long matchId, String command) {
        Match match = get(matchId);
        return this.update(PlayMatchManager.playFriendlyMatch(match, command));
    }

    @Override
    public void deleteAll() {
        dataStore.deleteAll();
    }

}