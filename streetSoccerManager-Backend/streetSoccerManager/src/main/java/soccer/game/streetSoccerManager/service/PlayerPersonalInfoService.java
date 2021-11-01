package soccer.game.streetSoccerManager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.model.entities.PlayerPersonalInfo;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IMatchInfoRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPlayerPersonalInfoRepository;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IPlayerPersonalInfoService;

import java.util.List;

@Service
public class PlayerPersonalInfoService implements IPlayerPersonalInfoService {
    private IPlayerPersonalInfoRepository dataStore;

    // todo change datastore
    public PlayerPersonalInfoService(@Qualifier("playerPersonalInfoFakeDatabase") IPlayerPersonalInfoRepository dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public List<PlayerPersonalInfo> getAll() {
        return dataStore.getAll();
    }

    @Override
    public PlayerPersonalInfo get(Long id) {
        return dataStore.get(id);
    }

    @Override
    public Boolean delete(Long id) {
        return dataStore.delete(id);
    }

    @Override
    public Boolean add(PlayerPersonalInfo playerPersonalInfo) {
        return dataStore.add(playerPersonalInfo);
    }

    @Override
    public Boolean update(PlayerPersonalInfo playerPersonalInfo) {
        return dataStore.update(playerPersonalInfo);
    }
}
