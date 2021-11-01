package soccer.game.streetSoccerManager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.model.entities.PlayerPositionInfo;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPlayerPositionInfoRepository;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IPlayerPositionInfoService;

import java.util.List;

@Service
public class PlayerPositionInfoService implements IPlayerPositionInfoService {
    private IPlayerPositionInfoRepository dataStore;

    public PlayerPositionInfoService(@Qualifier("playerPositionInfoJPADatabase")IPlayerPositionInfoRepository dataStore) {
        this.dataStore = dataStore;
    }


    @Override
    public List<PlayerPositionInfo> getAll() {
        return dataStore.getAll();
    }

    @Override
    public PlayerPositionInfo get(Long id) {
        return dataStore.get(id);
    }

    @Override
    public Boolean delete(Long id) {
        return dataStore.delete(id);
    }

    @Override
    public Boolean add(PlayerPositionInfo playerPositionInfo) {
        return dataStore.add(playerPositionInfo);
    }

    @Override
    public Boolean update(PlayerPositionInfo playerPositionInfo) {
        return dataStore.update(playerPositionInfo);
    }
}
