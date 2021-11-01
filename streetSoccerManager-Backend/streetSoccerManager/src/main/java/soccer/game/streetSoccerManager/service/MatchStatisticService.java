package soccer.game.streetSoccerManager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.model.entities.MatchStatistic;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IMatchInfoRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IMatchStatisticRepository;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IMatchStatisticService;

import java.util.List;

@Service
public class MatchStatisticService implements IMatchStatisticService {
    private IMatchStatisticRepository dataStore;

    // todo change datastore
    public MatchStatisticService(@Qualifier("matchStatisticFakeDatabase") IMatchStatisticRepository dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public List<MatchStatistic> getAll() {
        return dataStore.getAll();
    }

    @Override
    public MatchStatistic get(Long id) {
        return dataStore.get(id);
    }

    @Override
    public Boolean delete(Long id) {
        return dataStore.delete(id);
    }

    @Override
    public Boolean add(MatchStatistic matchStatistic) {
        return dataStore.add(matchStatistic);
    }

    @Override
    public Boolean update(MatchStatistic matchStatistic) {
        return dataStore.update(matchStatistic);
    }
}
