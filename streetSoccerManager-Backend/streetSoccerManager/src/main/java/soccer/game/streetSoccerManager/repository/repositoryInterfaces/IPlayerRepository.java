package soccer.game.streetSoccerManager.repository.repositoryInterfaces;

import soccer.game.streetSoccerManager.model.Player;

import java.util.List;

public interface IPlayerRepository {
    List<Player> getAll();
    Player get(int id);
    Boolean delete(int id);
    Boolean add(Player player);
    Boolean update(Player player);
}
