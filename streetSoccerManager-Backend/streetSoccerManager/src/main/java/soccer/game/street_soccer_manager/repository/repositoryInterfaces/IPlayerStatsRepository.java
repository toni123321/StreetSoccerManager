package soccer.game.street_soccer_manager.repository.repositoryInterfaces;

import soccer.game.street_soccer_manager.model.PlayerStats;

import java.util.List;

public interface IPlayerStatsRepository {
    List<PlayerStats> getAll();
    PlayerStats get(Long id);
    Boolean delete(Long id);
    Boolean add(PlayerStats playerStats);
    Boolean update(PlayerStats playerStats);
}
