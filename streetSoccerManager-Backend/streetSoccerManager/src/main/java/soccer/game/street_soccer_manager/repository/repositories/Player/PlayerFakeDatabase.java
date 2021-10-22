package soccer.game.street_soccer_manager.repository.repositories.Player;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import soccer.game.street_soccer_manager.repository.repositories.Position.PositionFakeDatabase;
import soccer.game.street_soccer_manager.repository.repositoryInterfaces.IPlayerRepository;
import soccer.game.street_soccer_manager.repository.repositoryInterfaces.IPositionRepository;
import soccer.game.street_soccer_manager.repository.repositoryInterfaces.ITeamRepository;
import soccer.game.street_soccer_manager.model.Player;
import soccer.game.street_soccer_manager.model.PlayerStats;
import soccer.game.street_soccer_manager.model.Team;
import soccer.game.street_soccer_manager.repository.repositories.Team.TeamFakeDatabase;

import java.util.*;

@Repository
public class PlayerFakeDatabase implements IPlayerRepository {
    @Getter
    private List<Player> players = new ArrayList<>();

    private ITeamRepository teamFakeDatabase = new TeamFakeDatabase();
    List<Team> teams = teamFakeDatabase.getAll();

    private IPositionRepository positionFakeDatabase = new PositionFakeDatabase();
    //List<Position> positions = positionFakeDatabase.getAll();

    public PlayerFakeDatabase() {
        //STARTING
        players.add(new Player(0l, 4,"Javi", "Diaz",
                new GregorianCalendar(1997, 5, 15), 150,
                positionFakeDatabase.get("GK"), positionFakeDatabase.get("GK"), 1, true,
                new PlayerStats(0l,65),  teams.get(0)));

        players.add(new Player(1l, 3,"Andres", "Dumitrescu",
                new GregorianCalendar(2001, 3, 11), 150,
                positionFakeDatabase.get("CB"), positionFakeDatabase.get("CB"), 2, true,
                new PlayerStats(1l,60),  teams.get(0)));

        players.add(new Player(2l, 2,"Ross", "Callachan",
                new GregorianCalendar(1993, 9, 4), 150,
                positionFakeDatabase.get("CM"), positionFakeDatabase.get("LM"), 6, true,
                new PlayerStats(2l,64),  teams.get(0)));

        players.add(new Player(3l, 1,"Xavi", "Simons",
                new GregorianCalendar(2003, 4, 21), 150,
                positionFakeDatabase.get("CM"), positionFakeDatabase.get("RM"), 10, true,
                new PlayerStats(3l,65),  teams.get(0)));

        players.add(new Player(4l, 0,"Florian", "Sotoca",
                new GregorianCalendar(2000, 1, 1), 150,
                positionFakeDatabase.get("ST"), positionFakeDatabase.get("ST"), 9, true,
                new PlayerStats(4l,72),  teams.get(0)));

        // RESERVES
        players.add(new Player(5l, 5,"Ilias", "Karargyris",
                new GregorianCalendar(1997, 5, 15), 150,
                positionFakeDatabase.get("GK"), positionFakeDatabase.get("SUB1"), 20, false,
                new PlayerStats(5l,65),  teams.get(0)));

        players.add(new Player(6l, 6,"Mateo", "Gabbia",
                new GregorianCalendar(2001, 3, 11), 150,
                positionFakeDatabase.get("CB"), positionFakeDatabase.get("SUB2"), 2, false,
                new PlayerStats(6l,60),  teams.get(0)));

        players.add(new Player(7l,7,"Arthur", "Read",
                new GregorianCalendar(1993, 9, 4), 150,
                positionFakeDatabase.get("CM"), positionFakeDatabase.get("SUB3"), 6, false,
                new PlayerStats(7l,64),  teams.get(0)));


        players.add(new Player(8l, 8,"Leonardo", "Colucci",
                new GregorianCalendar(2003, 4, 21), 150,
                positionFakeDatabase.get("CM"), positionFakeDatabase.get("SUB4"), 10, false,
                new PlayerStats(8l,65),  teams.get(0)));

        players.add(new Player(9l, 9,"Liam", "Cullen",
                new GregorianCalendar(2000, 1, 1), 150,
                positionFakeDatabase.get("ST"), positionFakeDatabase.get("SUB5"), 9, false,
                new PlayerStats(9l,64),  teams.get(0)));


    }


    @Override
    public List<Player> getAll() {
        Collections.sort(players);
        return players;

    }

    @Override
    public Player get(Long id) {
        for (Player player : players) {
            if (player.getId() == id)
                return player;
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
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
        oldPlayer.setDefaultPosition(player.getDefaultPosition());
        oldPlayer.setCurrentPosition(player.getCurrentPosition());
        oldPlayer.setKitNr(player.getKitNr());
        oldPlayer.setStarting(player.isStarting());
        oldPlayer.setPlayerStats(player.getPlayerStats());
        oldPlayer.setTeam(player.getTeam());

        return true;
    }
}
