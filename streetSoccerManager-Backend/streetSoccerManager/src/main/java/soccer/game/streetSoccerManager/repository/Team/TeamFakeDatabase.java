package soccer.game.streetSoccerManager.repository.Team;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.interfaces.repositoryInterfaces.ITeamRepository;
import soccer.game.streetSoccerManager.model.Formation;
import soccer.game.streetSoccerManager.model.Team;
import soccer.game.streetSoccerManager.model.User;
import soccer.game.streetSoccerManager.repository.Formation.FormationFakeDatabase;
import soccer.game.streetSoccerManager.repository.User.UserFakeDatabase;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TeamFakeDatabase implements ITeamRepository {
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
    public List<Team> getAll() {
        return teams;
    }

    @Override
    public Team get(int id) {
        return null;
    }

    @Override
    public Boolean delete(int id) {
        return null;
    }

    @Override
    public Boolean add(Team team) {
        return null;
    }

    @Override
    public Boolean update(Team team) {
        return null;
    }
}
