package soccer.game.streetSoccerManager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.model.entities.PlayerAdditionalInfo;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IMatchInfoRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPlayerAdditionalInfoRepository;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IPlayerAdditionalInfoService;

import java.util.List;

@Service
public class PlayerAdditionalInfoService implements IPlayerAdditionalInfoService {
    private IPlayerAdditionalInfoRepository dataStore;

    // todo change datastore
    public PlayerAdditionalInfoService(@Qualifier("playerAdditionalInfoFakeDatabase") IPlayerAdditionalInfoRepository dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public List<PlayerAdditionalInfo> getAll() {
        return dataStore.getAll();
    }

    @Override
    public PlayerAdditionalInfo get(Long id) {
        return dataStore.get(id);
    }

    @Override
    public Boolean delete(Long id) {
        return dataStore.delete(id);
    }

    @Override
    public Boolean add(PlayerAdditionalInfo playerAdditionalInfo) {
        return dataStore.add(playerAdditionalInfo);
    }

    @Override
    public Boolean update(PlayerAdditionalInfo playerAdditionalInfo) {
        return dataStore.update(playerAdditionalInfo);
    }
}
