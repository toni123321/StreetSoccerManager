package soccer.game.streetSoccerManager.repository.Player;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.interfaces.repositoryInterfaces.IPlayerRepository;
import soccer.game.streetSoccerManager.model.Player;
import soccer.game.streetSoccerManager.model.PlayerStats;
import soccer.game.streetSoccerManager.model.Team;
import soccer.game.streetSoccerManager.repository.Team.TeamFakeDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class PlayerFakeDatabase implements IPlayerRepository {
    @Getter
    private List<Player> players = new ArrayList<>();

    private TeamFakeDatabase teamFakeDatabase = new TeamFakeDatabase();
    List<Team> teams = teamFakeDatabase.getAll();

    public PlayerFakeDatabase() {
        players.add(new Player(1, "Xavi", "Simons",
                new Date(2003, 4, 21), 150,
                new PlayerStats(80),  teams.get(0)));
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
