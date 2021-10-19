package soccer.game.streetSoccerManager.repository.repositoryInterfaces;

import soccer.game.streetSoccerManager.model.Formation;
import soccer.game.streetSoccerManager.model.Team;

import java.util.List;

public interface ITeamRepository {
    List<Team> getAll();
    Team get(int id);
    Boolean delete(int id);
    Boolean add(Team team);
    Boolean update(Team team);

    Formation getDefaultFormation();
}
