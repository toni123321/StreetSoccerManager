package soccer.game.streetSoccerManager.service_interfaces;



import soccer.game.streetSoccerManager.model.dtos.PlayerDTO;
import soccer.game.streetSoccerManager.model.entities.Player;

import java.util.List;

public interface IPlayerService {
    List<PlayerDTO> getAll();
    PlayerDTO get(Long id);
    Boolean delete(Long id);
    PlayerDTO add(PlayerDTO player);
    PlayerDTO update(PlayerDTO player);
    List<PlayerDTO> getAllPlayersInTeam(Long teamId);
    List<PlayerDTO> getAllPlayersInTeamAvailableForSwapping(Long teamId, Long playerToSwapId);

    List<PlayerDTO> getStartingPlayers(Long teamId);
    List<PlayerDTO> getReserves(Long teamId);
}
