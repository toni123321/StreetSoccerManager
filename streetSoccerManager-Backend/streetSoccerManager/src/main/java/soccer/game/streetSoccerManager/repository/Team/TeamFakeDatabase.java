package soccer.game.streetSoccerManager.repository.Team;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.interfaces.repositoryInterfaces.IFormationRepository;
import soccer.game.streetSoccerManager.interfaces.repositoryInterfaces.ITeamRepository;
import soccer.game.streetSoccerManager.interfaces.repositoryInterfaces.IUserRepository;
import soccer.game.streetSoccerManager.model.Formation;
import soccer.game.streetSoccerManager.model.FrontendUser;
import soccer.game.streetSoccerManager.model.Team;
import soccer.game.streetSoccerManager.model.User;
import soccer.game.streetSoccerManager.repository.Formation.FormationFakeDatabase;
import soccer.game.streetSoccerManager.repository.User.UserFakeDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TeamFakeDatabase implements ITeamRepository {
    @Getter
    private List<Team> teams = new ArrayList<>();

    private IFormationRepository formationFakeDatabase = new FormationFakeDatabase();
    private List<Formation> formations = formationFakeDatabase.getAll();

    private IUserRepository userFakeDatabase = new UserFakeDatabase();
    private List<User> users = userFakeDatabase.getAll();



    public TeamFakeDatabase() {
        teams.add(new Team(0, "Real Madrid-Pro", formations.get(0), ((FrontendUser) users.get(0)).getNickname(),users.get(0)));
        teams.add(new Team(1,  "Barcelona", formations.get(1), "Test", users.get(2)));
        teams.add(new Team(2,  "Sevilla", formations.get(1), "Test", users.get(2)));
        teams.add(new Team(3,  "Juventus", formations.get(1), "Test", users.get(2)));

    }



    @Override
    public Formation getDefaultFormation() {
        return formations.stream().
                filter(formation -> formation.getName() == "1-2-1").
                collect(Collectors.toList()).get(0);
    }
    @Override
    public List<Team> getAll() {
        return teams;
    }

    @Override
    public Team get(int id) {
        for (Team team : teams) {
            if (team.getId() == id)
                return team;
        }
        return null;
    }

    @Override
    public Boolean delete(int id) {
        Team team = get(id);
        if (team == null){
            return false;
        }

        return teams.remove(team);
    }

    @Override
    public Boolean add(Team team) {
        if (this.get(team.getId() )!= null){
            return false;
        }
        teams.add(team);
        return true;
    }

    @Override
    public Boolean update(Team team) {
        Team oldTeam = this.get(team.getId());
        if (oldTeam == null) {
            return false;
        }
        oldTeam.setName(team.getName());
        oldTeam.setFormation(team.getFormation());
        oldTeam.setManager(team.getManager());
        oldTeam.setUser(team.getUser());
        return true;
    }
}
