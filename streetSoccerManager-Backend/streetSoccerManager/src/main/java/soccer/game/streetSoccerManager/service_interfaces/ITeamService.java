package soccer.game.streetSoccerManager.service_interfaces;

import soccer.game.streetSoccerManager.model.dtos.TeamDTO;
import soccer.game.streetSoccerManager.model.entities.Team;

import java.util.List;

public interface ITeamService {
    List<TeamDTO> getAll();
    TeamDTO get(Long id);
    Boolean delete(Long id);
    TeamDTO add(TeamDTO team);
    TeamDTO update(TeamDTO team);

    List<TeamDTO> getCustomTeams();
    List<TeamDTO> getOfficialTeams();
}
