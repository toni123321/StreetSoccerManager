package soccer.game.streetSoccerManager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.model.entities.Match;
import soccer.game.streetSoccerManager.repository_interfaces.IMatchRepository;
import soccer.game.streetSoccerManager.service_interfaces.IMatchService;

import java.util.List;

@Service
public class MatchService implements IMatchService {
    private IMatchRepository dataStore;

    // todo change datastore
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


    // play match - boolean
    @Override
    public Boolean add(Match match) {
///        if(dataStore.add(match) && playMatch)
////        {
////            return true;
////        }
////        if(get true)
////        {
////            delete
////        }
////        return false;
//        return dataStore.add(match);
        return true;

    }

    @Override
    public Boolean update(Match match) {
//        return dataStore.update(match);
        return true;
    }
}
