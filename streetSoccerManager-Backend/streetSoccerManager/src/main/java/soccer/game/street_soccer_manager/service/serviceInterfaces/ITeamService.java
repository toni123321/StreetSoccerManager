package soccer.game.street_soccer_manager.service.serviceInterfaces;

import soccer.game.street_soccer_manager.model.Team;

import java.util.List;

public interface ITeamService {
    List<Team> getAll();
    Team get(Long id);
    Boolean delete(Long id);
    Boolean add(Team team);
    Boolean update(Team team);

    List<Team> getCustomTeams();
    List<Team> getOfficialTeams();

    //List<Team> getOpponentsForFriendlyMatch();
}
