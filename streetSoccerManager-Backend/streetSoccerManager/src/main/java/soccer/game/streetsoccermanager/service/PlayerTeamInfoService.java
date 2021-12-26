package soccer.game.streetsoccermanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soccer.game.streetsoccermanager.model.entities.PlayerTeamInfo;
import soccer.game.streetsoccermanager.repository_interfaces.IPlayerTeamInfoRepository;
import soccer.game.streetsoccermanager.service_interfaces.IPlayerTeamInfoService;

import java.util.List;

@Service
public class PlayerTeamInfoService implements IPlayerTeamInfoService {
    private IPlayerTeamInfoRepository dataStore;

    @Autowired
    public PlayerTeamInfoService(IPlayerTeamInfoRepository dataStore) {
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
        if(get(id) != null) {
            dataStore.delete(id);
            return true;
        }
        return false;
    }

    @Override
    public PlayerTeamInfo add(PlayerTeamInfo playerTeamInfo) {
        if(playerTeamInfo.getId() == null) {
            return dataStore.add(playerTeamInfo);
        }
        return null;
    }

    @Override
    public PlayerTeamInfo update(PlayerTeamInfo playerTeamInfo) {
        if(playerTeamInfo.getId() != null) {
            return dataStore.update(playerTeamInfo);
        }
        return null;
    }

    @Override
    public void deleteAll() {
        dataStore.deleteAll();
    }

}
