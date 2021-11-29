package soccer.game.streetsocceranager.repository_interfaces;

import soccer.game.streetsocceranager.model.entities.Formation;
import soccer.game.streetsocceranager.model.entities.Team;

import java.util.List;

public interface ITeamRepository {
    List<Team> getAll();
    Team get(Long id);
    Boolean delete(Long id);
    Team add(Team team);
    Team update(Team team);

    Formation getDefaultFormation();
    void deleteAll();
}
