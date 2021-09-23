package soccer.game.streetSoccerManager.interfaces.repositoryInterfaces;

import soccer.game.streetSoccerManager.model.Team;

import java.util.List;

public interface ITeamRepository {
    List<Team> getAll();
    Team get(int id);
    Boolean delete(int id);
    Boolean add(Team team);
    Boolean update(Team team);
}
