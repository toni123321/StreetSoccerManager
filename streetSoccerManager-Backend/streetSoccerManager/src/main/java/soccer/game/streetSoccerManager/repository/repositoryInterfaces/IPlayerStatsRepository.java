package soccer.game.streetSoccerManager.repository.repositoryInterfaces;

import soccer.game.streetSoccerManager.model.PlayerStats;


import java.util.List;

public interface IPlayerStatsRepository {
    List<PlayerStats> getAll();
    PlayerStats get(Long id);
    Boolean delete(Long id);
    Boolean add(PlayerStats playerStats);
    Boolean update(PlayerStats playerStats);
}
