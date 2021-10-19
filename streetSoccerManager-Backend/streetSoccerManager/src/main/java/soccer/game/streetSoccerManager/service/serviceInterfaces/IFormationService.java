package soccer.game.streetSoccerManager.interfaces.serviceInterfaces;



import soccer.game.streetSoccerManager.model.Formation;
import soccer.game.streetSoccerManager.model.FormationPosition;

import java.util.List;

public interface IFormationService {
    List<Formation> getAll();
    Formation get(int id);
    Boolean delete(int id);
    Boolean add(Formation formation);
    Boolean update(Formation formation);
}

