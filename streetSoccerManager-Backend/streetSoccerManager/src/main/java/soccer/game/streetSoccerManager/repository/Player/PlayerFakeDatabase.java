package soccer.game.streetSoccerManager.repository.Player;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.interfaces.repositoryInterfaces.IPlayerRepository;
import soccer.game.streetSoccerManager.interfaces.repositoryInterfaces.ITeamRepository;
import soccer.game.streetSoccerManager.model.Player;
import soccer.game.streetSoccerManager.model.PlayerStats;
import soccer.game.streetSoccerManager.model.Team;
import soccer.game.streetSoccerManager.repository.Team.TeamFakeDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Repository
public class PlayerFakeDatabase implements IPlayerRepository {
    @Getter
    private List<Player> players = new ArrayList<>();

    private ITeamRepository teamFakeDatabase = new TeamFakeDatabase();
    List<Team> teams = teamFakeDatabase.getAll();

    public PlayerFakeDatabase() {
        //STARTING
        players.add(new Player(0, 4,"Javi", "Diaz",
                new Date(1997, 5, 15), 150,
                "GK", "GK", 1, true,
                new PlayerStats(0,65),  teams.get(0)));

        players.add(new Player(1, 3,"Andres", "Dumitrescu",
                new Date(2001, 3, 11), 150,
                "DEF", "DEF", 2, true,
                new PlayerStats(1,60),  teams.get(0)));
        players.add(new Player(2, 2,"Ross", "Callachan",
                new Date(1993, 9, 4), 150,
                "MID", "MID", 6, true,
                new PlayerStats(2,64),  teams.get(0)));
        players.add(new Player(3, 1,"Xavi", "Simons",
                new Date(2003, 4, 21), 150,
                "MID", "MID", 10, true,
                new PlayerStats(3,65),  teams.get(0)));
        players.add(new Player(4, 0,"Florian", "Sotoca",
                new Date(2000, 1, 1), 150,
                "ST", "ST", 9, true,
                new PlayerStats(4,72),  teams.get(0)));

        // RESERVES
        players.add(new Player(5, 5,"Ilias", "Karargyris",
                new Date(1997, 5, 15), 150,
                "GK", "SUB1", 20, false,
                new PlayerStats(5,65),  teams.get(0)));

        players.add(new Player(6, 6,"Mateo", "Gabbia",
                new Date(2001, 3, 11), 150,
                "DEF", "SUB2", 2, false,
                new PlayerStats(6,60),  teams.get(0)));

        players.add(new Player(7,7,"Arthur", "Read",
                new Date(1993, 9, 4), 150,
                "MID", "SUB3", 6, false,
                new PlayerStats(7,64),  teams.get(0)));


        players.add(new Player(8, 8,"Leonardo", "Colucci",
                new Date(2003, 4, 21), 150,
                "MID", "SUB4", 10, false,
                new PlayerStats(8,65),  teams.get(0)));

        players.add(new Player(9, 9,"Liam", "Cullen",
                new Date(2000, 1, 1), 150,
                "ST", "SUB5", 9, false,
                new PlayerStats(9,64),  teams.get(0)));


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
        oldPlayer.setPositionIndex(player.getPositionIndex());
        oldPlayer.setFirstName(player.getFirstName());
        oldPlayer.setLastName(player.getLastName());
        oldPlayer.setDob(player.getDob());
        oldPlayer.setPrice(player.getPrice());
        oldPlayer.setTeam(player.getTeam());

        return true;
    }
}
