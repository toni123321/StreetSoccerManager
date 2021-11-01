package soccer.game.streetSoccerManager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.model.entities.CustomTeam;
import soccer.game.streetSoccerManager.model.entities.Match;
import soccer.game.streetSoccerManager.model.entities.OfficialTeam;
import soccer.game.streetSoccerManager.model.entities.Team;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IMatchRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.ITeamRepository;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IMatchService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService implements IMatchService {
    private IMatchRepository dataStore;

    // todo change datastore
    public MatchService(@Qualifier("matchFakeDatabase") IMatchRepository dataStore) {
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
    public Boolean add(Match match) {
        return dataStore.add(match);
    }

    @Override
    public Boolean update(Match match) {
        return dataStore.update(match);
    }
}
