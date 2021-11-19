package soccer.game.streetSoccerManager.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.model.entities.PlayerPositionInfo;
import soccer.game.streetSoccerManager.repository_interfaces.IPlayerPositionInfoRepository;
import soccer.game.streetSoccerManager.service_interfaces.IPlayerPositionInfoService;

import java.util.List;

@Service
public class PlayerPositionInfoService implements IPlayerPositionInfoService {
    private IPlayerPositionInfoRepository dataStore;
    private ModelMapper modelMapper = new ModelMapper();

    public PlayerPositionInfoService(@Qualifier("playerPositionInfoJPADatabase")IPlayerPositionInfoRepository dataStore) {
        this.dataStore = dataStore;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
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
    public PlayerPositionInfo add(PlayerPositionInfo playerPositionInfo) {
        return dataStore.add(playerPositionInfo);
    }

    @Override
    public PlayerPositionInfo update(PlayerPositionInfo playerPositionInfo) {
        return dataStore.update(playerPositionInfo);
    }

}
