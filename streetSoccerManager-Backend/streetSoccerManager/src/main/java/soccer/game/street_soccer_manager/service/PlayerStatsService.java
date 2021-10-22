package soccer.game.street_soccer_manager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.street_soccer_manager.model.PlayerStats;
import soccer.game.street_soccer_manager.repository.repositoryInterfaces.IPlayerStatsRepository;
import soccer.game.street_soccer_manager.service.serviceInterfaces.IPlayerStatsService;

import java.util.List;

@Service
public class PlayerStatsService implements IPlayerStatsService {
    private IPlayerStatsRepository dataStore;

    public PlayerStatsService(@Qualifier("playerStatsDatabase")IPlayerStatsRepository dataStore) {
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
    public Boolean add(PlayerStats playerStats) {
        return dataStore.add(playerStats);
    }

    @Override
    public Boolean update(PlayerStats playerStats) {
        return dataStore.update(playerStats);
    }
}
