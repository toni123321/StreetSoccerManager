package soccer.game.streetSoccerManager.repository.Player;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.interfaces.repositoryInterfaces.IPlayerRepository;
import soccer.game.streetSoccerManager.model.Player;
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
    private List<Team> teams = teamFakeDatabase.getTeams();


    public PlayerFakeDatabase() {
        players.add(new Player(1, "Xavi", "Simons", new Date(2003, 4, 21), 150, teams.get(0)));
    }


    @Override
    public List<Player> getAll() {
        return players;
    }

    @Override
    public Player get(int id) {
        return null;
    }

    @Override
    public Boolean delete(int id) {
        return null;
    }

    @Override
    public Boolean add(Player player) {
        return null;
    }

    @Override
    public Boolean update(Player player) {
        return null;
    }
}
