package soccer.game.streetSoccerManager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.Interfaces.IRepository;
import soccer.game.streetSoccerManager.Interfaces.IService;
import soccer.game.streetSoccerManager.model.Team;
import soccer.game.streetSoccerManager.repository.FakeDatabase;
import soccer.game.streetSoccerManager.repository.TeamFakeDatabase;
import soccer.game.streetSoccerManager.repository.UserFakeDatabase;

import java.util.List;

@Service
public class TeamService implements IService {

    private List<Team> teams;




    private IRepository fakeDatabase;


    public TeamService(@Qualifier("teamFakeDatabase") IRepository fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
        this.teams = (List<Team>) fakeDatabase.getAll();
    }


    @Override
    public List<Team> getAll() {
        return teams;
    }

    @Override
    public Team get(Object id) {
        for (Team team : teams) {
            if (team.getId() == (int)id)
                return team;
        }
        return null;
    }

    @Override
    public Boolean delete(Object id) {
        Team team = (Team) get(id);
        if (team == null){
            return false;
        }

        return teams.remove(team);
    }

    @Override
    public Boolean add(Object team) {
        if (this.get(((Team) team).getId()) != null){
            return false;
        }
        teams.add(((Team) team));
        return true;
    }

    @Override
    public Boolean update(Object team) {
        Team oldTeam = (Team) this.get(((Team)team).getId());
        if (oldTeam == null) {
            return false;
        }
        oldTeam.setName(((Team) team).getName());
        oldTeam.setManager(((Team) team).getManager());
        return true;
    }


}
