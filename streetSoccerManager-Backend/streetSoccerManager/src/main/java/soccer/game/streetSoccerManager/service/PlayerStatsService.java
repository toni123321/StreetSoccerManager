package soccer.game.streetSoccerManager.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.model.entities.PlayerStats;
import soccer.game.streetSoccerManager.repository_interfaces.IPlayerStatsRepository;
import soccer.game.streetSoccerManager.service_interfaces.IPlayerStatsService;

import java.util.List;

@Service
public class PlayerStatsService implements IPlayerStatsService {
    private IPlayerStatsRepository dataStore;
    private ModelMapper modelMapper = new ModelMapper();

    public PlayerStatsService(@Qualifier("playerStatsJPADatabase")IPlayerStatsRepository dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public List<PlayerStats> getAll() {
        return dataStore.getAll();
    }

    @Override
    public PlayerStats get(Long id) {
        return dataStore.get(id);
    }

    @Override
    public Boolean delete(Long id) {
        return dataStore.delete(id);
    }

    @Override
    public PlayerStats add(PlayerStats stat) {
        return dataStore.add(stat);
    }

    @Override
    public PlayerStats update(PlayerStats stat) {
        return dataStore.update(stat);
    }

    @Override
    public void deleteAll() {
        dataStore.deleteAll();
    }

}
