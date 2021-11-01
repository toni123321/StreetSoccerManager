package soccer.game.streetSoccerManager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.model.entities.MatchInfo;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IMatchInfoRepository;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IMatchInfoService;

import java.util.List;

@Service
public class MatchInfoService implements IMatchInfoService {
    private IMatchInfoRepository dataStore;

    // todo change datastore
    public MatchInfoService(@Qualifier("matchInfoStubDatabase") IMatchInfoRepository dataStore) {
        this.dataStore = dataStore;
    }


    @Override
    public List<MatchInfo> getAll() {
        return dataStore.getAll();
    }

    @Override
    public MatchInfo get(Long id) {
        return dataStore.get(id);
    }

    @Override
    public Boolean delete(Long id) {
        return dataStore.delete(id);
    }

    @Override
    public Boolean add(MatchInfo matchInfo) {
        return dataStore.add(matchInfo);
    }

    @Override
    public Boolean update(MatchInfo matchInfo) {
        return dataStore.update(matchInfo);
    }
}
