package soccer.game.streetSoccerManager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.model.entities.PlayerAdditionalInfo;
import soccer.game.streetSoccerManager.repository_interfaces.IPlayerAdditionalInfoRepository;
import soccer.game.streetSoccerManager.service_interfaces.IPlayerAdditionalInfoService;

import java.util.List;

@Service
public class PlayerAdditionalInfoService implements IPlayerAdditionalInfoService {
    private IPlayerAdditionalInfoRepository dataStore;

    // todo change datastore
    public PlayerAdditionalInfoService(@Qualifier("playerAdditionalInfoJPADatabase") IPlayerAdditionalInfoRepository dataStore) {
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
    public PlayerAdditionalInfo add(PlayerAdditionalInfo playerAdditionalInfo) {
        return dataStore.add(playerAdditionalInfo);
    }

    @Override
    public PlayerAdditionalInfo update(PlayerAdditionalInfo playerAdditionalInfo) {
        return dataStore.update(playerAdditionalInfo);
    }

}
