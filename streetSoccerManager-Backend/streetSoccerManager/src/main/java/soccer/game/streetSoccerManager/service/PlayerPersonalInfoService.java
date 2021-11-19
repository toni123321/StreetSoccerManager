package soccer.game.streetSoccerManager.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.model.entities.PlayerPersonalInfo;
import soccer.game.streetSoccerManager.repository_interfaces.IPlayerPersonalInfoRepository;
import soccer.game.streetSoccerManager.service_interfaces.IPlayerPersonalInfoService;

import java.util.List;

@Service
public class PlayerPersonalInfoService implements IPlayerPersonalInfoService {
    private IPlayerPersonalInfoRepository dataStore;
    private ModelMapper modelMapper = new ModelMapper();

    // todo change datastore
    public PlayerPersonalInfoService(@Qualifier("playerPersonalInfoJPADatabase") IPlayerPersonalInfoRepository dataStore) {
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
    public PlayerPersonalInfo add(PlayerPersonalInfo playerPersonalInfo) {
        return dataStore.add(playerPersonalInfo);
    }

    @Override
    public PlayerPersonalInfo update(PlayerPersonalInfo playerPersonalInfo) {
        return dataStore.update(playerPersonalInfo);
    }

}
