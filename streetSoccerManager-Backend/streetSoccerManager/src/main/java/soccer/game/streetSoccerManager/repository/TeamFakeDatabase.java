package soccer.game.streetSoccerManager.repository;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.Interfaces.IRepository;
import soccer.game.streetSoccerManager.model.Formation;
import soccer.game.streetSoccerManager.model.Team;
import soccer.game.streetSoccerManager.model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TeamFakeDatabase implements IRepository {
    @Getter
    private List<Team> teams = new ArrayList<>();

    private FormationFakeDatabase formationFakeDatabase = new FormationFakeDatabase();
    private List<Formation> formations = formationFakeDatabase.getFormations();

    private UserFakeDatabase userFakeDatabase = new UserFakeDatabase();
    private List<User> users = userFakeDatabase.getUsers();



    public TeamFakeDatabase() {
        teams.add(new Team(1, "Real Madrid-Pro", formations.get(0) ,users.get(0)));
        teams.add(new Team(2, "newBarca", formations.get(1), users.get(1)));

    }

    @Override
    public Object getAll() {
        return teams;
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
