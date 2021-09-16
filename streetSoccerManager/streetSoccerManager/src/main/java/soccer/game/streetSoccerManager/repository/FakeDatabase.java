package soccer.game.streetSoccerManager.repository;

import lombok.Getter;
import soccer.game.streetSoccerManager.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FakeDatabase {

    @Getter
    private List<User> users = new ArrayList<>();

    @Getter
    private List<Team> teams = new ArrayList<>();

    @Getter
    private List<Player> players = new ArrayList<>();

    @Getter
    private List<Formation> formations = new ArrayList<>();





    public FakeDatabase() {
        users.add(new FrontendUser(1, "peter@gmail.com", "123", "pete", 100));
        users.add(new FrontendUser(2, "john@gmail.com", "456", "jo", 10));

        users.add(new Admin(3, "admin1@gmail.com", "admin1", "Admin1", "Admin1"));

        formations.add(new Formation(1, "1-2-1"));
        formations.add(new Formation(2, "2-1-1"));


        teams.add(new Team(1, "Real Madrid-Pro", formations.get(0) ,users.get(0)));
        teams.add(new Team(2, "newBarca", formations.get(1), users.get(1)));


        players.add(new Player(1, "Xavi", "Simons", new Date(2003, 4, 21), 150, teams.get(0)));

    }


}
