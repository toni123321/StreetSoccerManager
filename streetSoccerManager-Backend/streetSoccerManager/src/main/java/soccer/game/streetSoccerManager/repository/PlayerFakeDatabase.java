package soccer.game.streetSoccerManager.repository;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.Interfaces.IRepository;
import soccer.game.streetSoccerManager.model.Player;
import soccer.game.streetSoccerManager.model.Team;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class PlayerFakeDatabase implements IRepository {
    @Getter
    private List<Player> players = new ArrayList<>();

    private TeamFakeDatabase teamFakeDatabase = new TeamFakeDatabase();
    private List<Team> teams = teamFakeDatabase.getTeams();


    public PlayerFakeDatabase() {
        players.add(new Player(1, "Xavi", "Simons", new Date(2003, 4, 21), 150, teams.get(0)));
    }

    @Override
    public Object getAll() {
        return players;
    }

    @Override
    public Object get(Object obj) {
        return null;
    }

    @Override
    public Object delete(Object obj) {
        return null;
    }

    @Override
    public Object add(Object obj) {
        return null;
    }

    @Override
    public Object update(Object obj) {
        return null;
    }
}
