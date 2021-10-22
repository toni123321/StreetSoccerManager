package soccer.game.street_soccer_manager.repository.repositories.Team;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import soccer.game.street_soccer_manager.model.*;
import soccer.game.street_soccer_manager.repository.repositoryInterfaces.IFormationRepository;
import soccer.game.street_soccer_manager.repository.repositoryInterfaces.ITeamRepository;
import soccer.game.street_soccer_manager.repository.repositoryInterfaces.IUserRepository;
import soccer.game.street_soccer_manager.repository.repositories.Formation.FormationFakeDatabase;
import soccer.game.street_soccer_manager.repository.repositories.User.UserFakeDatabase;

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
        teams.add(new CustomTeam(0l, "Real Madrid-Pro", formations.get(0), users.get(0)));
        teams.add(new OfficialTeam(1l,  "Barcelona", formations.get(1), "Manager1"));
        teams.add(new OfficialTeam(2l,  "Sevilla", formations.get(1), "Manager2"));
        teams.add(new OfficialTeam(3l,  "Juventus", formations.get(1), "Manager3"));
    }

    @Override
    public Formation getDefaultFormation() {
        return formations.stream().
                filter(formation -> formation.getName().equals("1-2-1")).
                collect(Collectors.toList()).get(0);
    }
    @Override
    public List<Team> getAll() {
        return teams;
    }

    @Override
    public Team get(Long id) {
        for (Team team : teams) {
            if (team.getId() == id)
                return team;
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        Team team = get(id);
        if (team == null){
            return false;
        }

        return teams.remove(team);
    }

    @Override
    public Boolean add(Team team) {
        if(team.getId() == null) {
            team.setId(Long.valueOf(teams.size()));
        }
        else if (this.get(team.getId() )!= null){
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
        if(oldTeam instanceof CustomTeam) {
            ((CustomTeam) oldTeam).setManager(((CustomTeam) team).getManager());
        }
        if(oldTeam instanceof OfficialTeam){
            ((OfficialTeam) oldTeam).setManagerName(((OfficialTeam) team).getManagerName());
        }
        return true;
    }
}
