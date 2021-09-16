package soccer.game.streetSoccerManager.service;

import soccer.game.streetSoccerManager.model.Player;
import soccer.game.streetSoccerManager.model.Team;
import soccer.game.streetSoccerManager.repository.FakeDatabase;

import java.util.List;

public class PlayerService {

    private List<Player> players;
    private FakeDatabase fakeDatabase = new FakeDatabase();

    public PlayerService() {
        this.players = fakeDatabase.getPlayers();
    }


    public List<Player> getAllPlayers() {
        return players;
    }

    public Player getPlayer(int id) {
        for (Player player : players) {
            if (player.getId() == id)
                return player;
        }
        return null;
    }

    public boolean deletePlayer(int id) {
        Player player = getPlayer(id);
        if (player == null){
            return false;
        }

        return players.remove(player);
    }


    public boolean addPlayer(Player player) {
        if (this.getPlayer(player.getId()) != null){
            return false;
        }
        players.add(player);
        return true;
    }

    public boolean updatePlayer(Player player) {
        Player oldPlayer = this.getPlayer(player.getId());
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
