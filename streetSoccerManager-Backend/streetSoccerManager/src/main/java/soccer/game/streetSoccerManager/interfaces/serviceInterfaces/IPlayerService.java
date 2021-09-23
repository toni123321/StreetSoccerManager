package soccer.game.streetSoccerManager.interfaces.serviceInterfaces;



import soccer.game.streetSoccerManager.model.Player;

import java.util.List;

public interface IPlayerService {
    List<Player> getAll();
    Player get(int id);
    Boolean delete(int id);
    Boolean add(Player player);
    Boolean update(Player player);
}
