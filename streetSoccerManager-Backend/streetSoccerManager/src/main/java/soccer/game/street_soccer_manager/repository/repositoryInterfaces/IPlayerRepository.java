package soccer.game.street_soccer_manager.repository.repositoryInterfaces;

import soccer.game.street_soccer_manager.model.Player;

import java.util.List;

public interface IPlayerRepository {
    List<Player> getAll();
    Player get(Long id);
    Boolean delete(Long id);
    Boolean add(Player player);
    Boolean update(Player player);
}
