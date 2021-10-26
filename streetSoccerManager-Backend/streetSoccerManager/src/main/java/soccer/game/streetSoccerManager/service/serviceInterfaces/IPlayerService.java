package soccer.game.streetSoccerManager.service.serviceInterfaces;



import soccer.game.streetSoccerManager.model.entities.Player;

import java.util.List;

public interface IPlayerService {
    List<Player> getAll();
    Player get(Long id);
    Boolean delete(Long id);
    Boolean add(Player player);
    Boolean update(Player player);
    List<Player> getAllPlayersInTeam(Long teamId);
    List<Player> getAllPlayersInTeamAvailableForSwapping(Long teamId, Long playerToSwapId);

    List<Player> getStartingPlayers(Long teamId);
    List<Player> getReserves(Long teamId);
}
