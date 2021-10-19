package soccer.game.streetSoccerManager.service.serviceInterfaces;



import soccer.game.streetSoccerManager.model.Formation;

import java.util.List;

public interface IFormationService {
    List<Formation> getAll();
    Formation get(int id);
    Boolean delete(int id);
    Boolean add(Formation formation);
    Boolean update(Formation formation);
}

