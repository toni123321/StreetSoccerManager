package soccer.game.streetSoccerManager.repository.repositories.PlayerStats;

import soccer.game.streetSoccerManager.model.PlayerStats;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPlayerStatsRepository;

import java.util.List;

public class PlayerStatsFakeDatabase implements IPlayerStatsRepository {

    // todo: Implement methods, add constructor with faked objects
    @Override
    public List<PlayerStats> getAll() {
        return null;
    }

    @Override
    public PlayerStats get(Long id) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public Boolean add(PlayerStats playerStats) {
        return null;
    }

    @Override
    public Boolean update(PlayerStats playerStats) {
        return null;
    }
}
