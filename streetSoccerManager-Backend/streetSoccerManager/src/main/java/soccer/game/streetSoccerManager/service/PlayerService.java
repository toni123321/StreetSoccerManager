package soccer.game.streetSoccerManager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.Interfaces.IRepository;
import soccer.game.streetSoccerManager.Interfaces.IService;
import soccer.game.streetSoccerManager.model.Player;
import soccer.game.streetSoccerManager.model.Team;
import soccer.game.streetSoccerManager.repository.FakeDatabase;

import java.util.List;

@Service
public class PlayerService implements IService {

    private List<Player> players;



    private IRepository fakeDatabase;

    public PlayerService(@Qualifier("playerFakeDatabase") IRepository fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
        this.players = (List<Player>) fakeDatabase.getAll();
    }



    @Override
    public List<Player> getAll() {
        return players;
    }

    @Override
    public Player get(Object id) {
        for (Player player : players) {
            if (player.getId() == (int) id)
                return player;
        }
        return null;
    }

    @Override
    public Boolean delete(Object id) {
        Player player = get(id);
        if (player == null){
            return false;
        }

        return players.remove(player);
    }

    @Override
    public Boolean add(Object player) {
        if (this.get(((Player) player).getId()) != null){
            return false;
        }
        players.add(((Player) player));
        return true;
    }

    @Override
    public Boolean update(Object player) {
        Player oldPlayer = this.get(((Player) player).getId());
        if (oldPlayer == null) {
            return false;
        }
        oldPlayer.setFirstName(((Player) player).getFirstName());
        oldPlayer.setLastName(((Player) player).getLastName());
        oldPlayer.setDob(((Player) player).getDob());
        oldPlayer.setPrice(((Player) player).getPrice());
        oldPlayer.setTeam(((Player) player).getTeam());

        return true;
    }

   
}
