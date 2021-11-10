package soccer.game.streetSoccerManager.repository_interfaces;

import soccer.game.streetSoccerManager.model.entities.PlayerStats;


import java.util.List;

public interface IPlayerStatsRepository {
    List<PlayerStats> getAll();
    PlayerStats get(Long id);
    Boolean delete(Long id);
    Boolean add(PlayerStats playerStats);
    Boolean update(PlayerStats playerStats);
    void deleteAll();
}
