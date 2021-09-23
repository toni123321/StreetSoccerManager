package soccer.game.streetSoccerManager.interfaces.repositoryInterfaces;

import soccer.game.streetSoccerManager.model.Formation;
import soccer.game.streetSoccerManager.model.User;

import java.util.List;

public interface IFormationRepository {
    List<Formation> getAll();
    Formation get(int id);
    Boolean delete(int id);
    Boolean add(Formation formation);
    Boolean update(Formation formation);
}
