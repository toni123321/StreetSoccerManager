package soccer.game.streetSoccerManager.model.converters;

import soccer.game.streetSoccerManager.model.dtos.PlayerDTO;
import soccer.game.streetSoccerManager.model.dtos.PlayerStatsDTO;
import soccer.game.streetSoccerManager.model.entities.Player;
import soccer.game.streetSoccerManager.model.entities.PlayerStats;

public class PlayerStatsConverter {
    public PlayerStats convertPlayerStatsDtoToPlayerStats(PlayerStatsDTO playerStatsDTO){
        return new PlayerStats(playerStatsDTO.getId(), playerStatsDTO.getOverallRating());
    }
    public PlayerStatsDTO convertPlayerStatsToPlayerStatsDto(PlayerStats playerStats) {
        return new PlayerStatsDTO(playerStats.getId(), playerStats.getOverallRating());
    }
}
