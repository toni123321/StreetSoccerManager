package soccer.game.streetSoccerManager.repository;

import soccer.game.streetSoccerManager.model.FrontendUser;
import soccer.game.streetSoccerManager.model.Player;
import soccer.game.streetSoccerManager.model.Team;
import soccer.game.streetSoccerManager.model.User;

import java.util.ArrayList;
import java.util.List;

public class FakeDatabase {
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public List<Player> getPlayers() {
        return players;
    }

    private List<Team> teams = new ArrayList<>();
    private List<Player> players = new ArrayList<>();


    public FakeDatabase() {
        users.add(new FrontendUser(1, "peter@gmail.com", "123", "pete"));
        users.add(new FrontendUser(2, "john@gmail.com", "456", "jo"));

        teams.add(new Team(1l, "Real Madrid-Pro", users.get(0)));
        teams.add(new Team(2l, "newBarca", users.get(0)));

    }


}
