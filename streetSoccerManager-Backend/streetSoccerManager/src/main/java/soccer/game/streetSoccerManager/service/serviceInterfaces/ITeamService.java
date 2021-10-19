package soccer.game.streetSoccerManager.service.serviceInterfaces;

import soccer.game.streetSoccerManager.model.Team;

import java.util.List;

public interface ITeamService {
    List<Team> getAll();
    Team get(int id);
    Boolean delete(int id);
    Boolean add(Team team);
    Boolean update(Team team);

    //List<Team> getOpponentsForFriendlyMatch();
}
