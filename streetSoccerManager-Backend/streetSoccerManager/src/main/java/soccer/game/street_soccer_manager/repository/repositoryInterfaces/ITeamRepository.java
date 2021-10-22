package soccer.game.street_soccer_manager.repository.repositoryInterfaces;

import soccer.game.street_soccer_manager.model.Formation;
import soccer.game.street_soccer_manager.model.Team;

import java.util.List;

public interface ITeamRepository {
    List<Team> getAll();
    Team get(Long id);
    Boolean delete(Long id);
    Boolean add(Team team);
    Boolean update(Team team);

    Formation getDefaultFormation();
}
