package soccer.game.streetSoccerManager.service_interfaces;

import soccer.game.streetSoccerManager.model.entities.PlayerStats;


import java.util.List;

public interface IPlayerStatsService {
    List<PlayerStats> getAll();
    PlayerStats get(Long id);
    Boolean delete(Long id);
    PlayerStats add(PlayerStats playerStats);
    PlayerStats update(PlayerStats playerStats);
    void deleteAll();
}
