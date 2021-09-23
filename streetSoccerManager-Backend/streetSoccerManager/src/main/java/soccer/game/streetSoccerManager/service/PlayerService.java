package soccer.game.streetSoccerManager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.interfaces.repositoryInterfaces.IPlayerRepository;
import soccer.game.streetSoccerManager.interfaces.serviceInterfaces.IPlayerService;
import soccer.game.streetSoccerManager.model.Player;

import java.util.List;

@Service
public class PlayerService implements IPlayerService {

    private List<Player> players;



    private IPlayerRepository fakeDatabase;

    public PlayerService(@Qualifier("playerFakeDatabase") IPlayerRepository fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
        this.players = fakeDatabase.getAll();
    }



    @Override
    public List<Player> getAll() {
        return players;
    }

    @Override
    public Player get(int id) {
        for (Player player : players) {
            if (player.getId() == id)
                return player;
        }
        return null;
    }

    @Override
    public Boolean delete(int id) {
        Player player = get(id);
        if (player == null){
            return false;
        }

        return players.remove(player);
    }

    @Override
    public Boolean add(Player player) {
        if (this.get(player.getId()) != null){
            return false;
        }
        players.add(player);
        return true;
    }

    @Override
    public Boolean update(Player player) {
        Player oldPlayer = this.get(player.getId());
        if (oldPlayer == null) {
            return false;
        }
        oldPlayer.setFirstName(player.getFirstName());
        oldPlayer.setLastName(player.getLastName());
        oldPlayer.setDob(player.getDob());
        oldPlayer.setPrice(player.getPrice());
        oldPlayer.setTeam(player.getTeam());

        return true;
    }

   
}
