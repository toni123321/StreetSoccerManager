package soccer.game.street_soccer_manager.service.serviceInterfaces;

import soccer.game.street_soccer_manager.model.PlayerStats;

import java.util.List;

public interface IPlayerStatsService {
    List<PlayerStats> getAll();
    PlayerStats get(Long id);
    Boolean delete(Long id);
    Boolean add(PlayerStats playerStats);
    Boolean update(PlayerStats playerStats);
}
