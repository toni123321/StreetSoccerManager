package soccer.game.streetSoccerManager.repository_interfaces;

import soccer.game.streetSoccerManager.model.entities.Formation;
import soccer.game.streetSoccerManager.model.entities.Team;

import java.util.List;

public interface ITeamRepository {
    List<Team> getAll();
    Team get(Long id);
    Boolean delete(Long id);
    Boolean add(Team team);
    Boolean update(Team team);

    Formation getDefaultFormation();
    void deleteAll();
}
