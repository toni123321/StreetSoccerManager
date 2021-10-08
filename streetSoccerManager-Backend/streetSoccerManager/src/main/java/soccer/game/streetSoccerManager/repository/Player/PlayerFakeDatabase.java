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
        //STARTING
        players.add(new Player(0,"Javi", "Diaz",
                new Date(1997, 5, 15), 150,
                "GK", "GK", 1, true,
                new PlayerStats(65),  teams.get(0)));

        players.add(new Player(1,"Andres", "Dumitrescu",
                new Date(2001, 3, 11), 150,
                "DEF", "DEF", 2, true,
                new PlayerStats(60),  teams.get(0)));
        players.add(new Player(2,"Ross", "Callachan",
                new Date(1993, 9, 4), 150,
                "MID", "MID", 6, true,
                new PlayerStats(64),  teams.get(0)));
        players.add(new Player(3,"Xavi", "Simons",
                new Date(2003, 4, 21), 150,
                "MID", "MID", 10, true,
                new PlayerStats(65),  teams.get(0)));
        players.add(new Player(4,"Florian", "Sotoca",
                new Date(2000, 1, 1), 150,
                "ST", "ST", 9, true,
                new PlayerStats(72),  teams.get(0)));

        // RESERVES
        players.add(new Player(5,"Ilias", "Karargyris",
                new Date(1997, 5, 15), 150,
                "GK", "GK", 20, false,
                new PlayerStats(65),  teams.get(0)));

        players.add(new Player(6,"Mateo", "Gabbia",
                new Date(2001, 3, 11), 150,
                "DEF", "DEF", 2, false,

                new PlayerStats(60),  teams.get(0)));
        players.add(new Player(7,"Arthur", "Read",
                new Date(1993, 9, 4), 150,
                "MID", "MID", 6, false,
                new PlayerStats(64),  teams.get(0)));
        players.add(new Player(8,"Leonardo", "Colucci",
                new Date(2003, 4, 21), 150,
                "MID", "MID", 10, false,
                new PlayerStats(65),  teams.get(0)));
        players.add(new Player(9,"Liam", "Cullen",
                new Date(2000, 1, 1), 150,
                "ST", "ST", 9, false,
                new PlayerStats(64),  teams.get(0)));
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
