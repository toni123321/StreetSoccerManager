package soccer.game.streetsocceranager.repository_interfaces;

import soccer.game.streetsocceranager.model.entities.PlayerStats;


import java.util.List;

public interface IPlayerStatsRepository {
    List<PlayerStats> getAll();
    PlayerStats get(Long id);
    Boolean delete(Long id);
    PlayerStats add(PlayerStats playerStats);
    PlayerStats update(PlayerStats playerStats);
    void deleteAll();
}
