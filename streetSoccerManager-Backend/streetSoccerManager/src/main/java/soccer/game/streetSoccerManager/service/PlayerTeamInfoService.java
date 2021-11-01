package soccer.game.streetSoccerManager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.model.entities.PlayerTeamInfo;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPlayerTeamInfoRepository;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IPlayerTeamInfoService;

import java.util.List;

@Service
public class PlayerTeamInfoService implements IPlayerTeamInfoService {
    private IPlayerTeamInfoRepository dataStore;

    // todo change datastore
    public PlayerTeamInfoService(@Qualifier("playerTeamInfoStubDatabase") IPlayerTeamInfoRepository dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public List<PlayerTeamInfo> getAll() {
        return dataStore.getAll();
    }

    @Override
    public PlayerTeamInfo get(Long id) {
        return dataStore.get(id);
    }

    @Override
    public Boolean delete(Long id) {
        return dataStore.delete(id);
    }

    @Override
    public Boolean add(PlayerTeamInfo playerTeamInfo) {
        return dataStore.add(playerTeamInfo);
    }

    @Override
    public Boolean update(PlayerTeamInfo playerTeamInfo) {
        return dataStore.update(playerTeamInfo);
    }
}
