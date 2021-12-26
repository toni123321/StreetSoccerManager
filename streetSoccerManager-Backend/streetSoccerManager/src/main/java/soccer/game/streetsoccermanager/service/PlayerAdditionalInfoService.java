package soccer.game.streetsoccermanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetsoccermanager.model.entities.PlayerAdditionalInfo;
import soccer.game.streetsoccermanager.repository_interfaces.IPlayerAdditionalInfoRepository;
import soccer.game.streetsoccermanager.service_interfaces.IPlayerAdditionalInfoService;

import java.util.List;

@Service
public class PlayerAdditionalInfoService implements IPlayerAdditionalInfoService {
    private IPlayerAdditionalInfoRepository dataStore;

    @Autowired
    public PlayerAdditionalInfoService(IPlayerAdditionalInfoRepository dataStore) {
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
        if(get(id) != null) {
            dataStore.delete(id);
            return true;
        }
        return false;
    }

    @Override
    public PlayerAdditionalInfo add(PlayerAdditionalInfo playerAdditionalInfo) {
        if(playerAdditionalInfo.getId() == null) {
            return dataStore.add(playerAdditionalInfo);
        }
        return null;
    }

    @Override
    public PlayerAdditionalInfo update(PlayerAdditionalInfo playerAdditionalInfo) {
        if(playerAdditionalInfo.getId() != null) {
            return dataStore.update(playerAdditionalInfo);
        }
        return null;
    }

    @Override
    public void deleteAll() {
        dataStore.deleteAll();
    }

}
