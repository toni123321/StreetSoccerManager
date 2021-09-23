package soccer.game.streetSoccerManager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.interfaces.repositoryInterfaces.ITeamRepository;
import soccer.game.streetSoccerManager.interfaces.serviceInterfaces.ITeamService;
import soccer.game.streetSoccerManager.model.Team;

import java.util.List;

@Service
public class TeamService implements ITeamService {

    private List<Team> teams;


    private ITeamRepository fakeDatabase;


    public TeamService(@Qualifier("teamFakeDatabase") ITeamRepository fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
        this.teams = fakeDatabase.getAll();
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
        oldTeam.setManager(team.getManager());
        return true;
    }


}
