package soccer.game.streetSoccerManager.repository_interfaces;

import soccer.game.streetSoccerManager.model.entities.Player;

import java.util.List;

public interface IPlayerRepository {
    List<Player> getAll();
    Player get(Long id);
    Boolean delete(Long id);
    Player add(Player player);
    Player update(Player player);
    void deleteAll();
}
