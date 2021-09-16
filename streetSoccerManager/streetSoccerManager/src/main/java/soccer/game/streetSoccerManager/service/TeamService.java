package soccer.game.streetSoccerManager.service;

import soccer.game.streetSoccerManager.model.Team;
import soccer.game.streetSoccerManager.repository.FakeDatabase;

import java.util.List;

public class TeamService {

    private List<Team> teams;
    private FakeDatabase fakeDatabase = new FakeDatabase();

    public TeamService() {
        this.teams = fakeDatabase.getTeams();
    }


    public List<Team> getAllTeams() {
        return teams;
    }

    public Team getTeam(Long id) {
        for (Team team : teams) {
            if (team.getId() == id)
                return team;
        }
        return null;
    }

    public boolean deleteTeam(Long id) {
        Team team = getTeam(id);
        if (team == null){
            return false;
        }

        return teams.remove(team);
    }


    public boolean addTeam(Team team) {
        if (this.getTeam(team.getId()) != null){
            return false;
        }
        teams.add(team);
        return true;
    }

    public boolean updateTeam(Team team) {
        Team oldTeam = this.getTeam(team.getId());
        if (oldTeam == null) {
            return false;
        }
        oldTeam.setName(team.getName());
        oldTeam.setManager(team.getManager());
        return true;
    }
}
