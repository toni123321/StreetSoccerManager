package soccer.game.streetSoccerManager.service.serviceInterfaces;

import soccer.game.streetSoccerManager.model.PlayerStats;


import java.util.List;

public interface IPlayerStatsService {
    List<PlayerStats> getAll();
    PlayerStats get(Long id);
    Boolean delete(Long id);
    Boolean add(PlayerStats playerStats);
    Boolean update(PlayerStats playerStats);
}
