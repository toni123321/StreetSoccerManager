package soccer.game.streetSoccerManager.service.serviceInterfaces;



import soccer.game.streetSoccerManager.model.entities.Formation;

import java.util.List;

public interface IFormationService {
    List<Formation> getAll();
    Formation get(Long id);
    Boolean delete(Long id);
    Boolean add(Formation formation);
    Boolean update(Formation formation);
}

