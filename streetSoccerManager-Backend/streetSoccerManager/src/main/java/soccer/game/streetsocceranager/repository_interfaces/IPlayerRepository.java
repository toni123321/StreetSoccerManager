package soccer.game.streetsocceranager.repository_interfaces;

import soccer.game.streetsocceranager.model.entities.Player;

import java.util.List;

public interface IPlayerRepository {
    List<Player> getAll();
    Player get(Long id);
    Boolean delete(Long id);
    Player add(Player player);
    Player update(Player player);
    void deleteAll();
}
