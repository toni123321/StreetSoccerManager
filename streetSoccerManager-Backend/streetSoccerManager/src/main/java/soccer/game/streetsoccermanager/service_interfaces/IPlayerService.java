package soccer.game.streetsoccermanager.service_interfaces;



import soccer.game.streetsoccermanager.model.entities.Player;

import java.util.List;

public interface IPlayerService {
    List<Player> getAll();
    Player get(Long id);
    Boolean delete(Long id);
    Player add(Player player);
    Player update(Player player);
    List<Player> getAllPlayersInTeam(Long teamId);
    List<Player> getAllPlayersInTeamAvailableForSwapping(Long teamId, Long playerToSwapId);

    List<Player> getStartingPlayers(Long teamId);
    List<Player> getReserves(Long teamId);
    void deleteAll();
}
