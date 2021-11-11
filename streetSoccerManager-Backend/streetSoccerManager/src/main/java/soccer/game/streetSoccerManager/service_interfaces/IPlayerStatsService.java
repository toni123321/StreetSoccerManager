package soccer.game.streetSoccerManager.service_interfaces;

import soccer.game.streetSoccerManager.model.dtos.PlayerStatsDTO;
import soccer.game.streetSoccerManager.model.entities.PlayerStats;


import java.util.List;

public interface IPlayerStatsService {
    List<PlayerStatsDTO> getAll();
    PlayerStatsDTO get(Long id);
    Boolean delete(Long id);
    PlayerStatsDTO add(PlayerStatsDTO playerStats);
    PlayerStatsDTO update(PlayerStatsDTO playerStats);
}
