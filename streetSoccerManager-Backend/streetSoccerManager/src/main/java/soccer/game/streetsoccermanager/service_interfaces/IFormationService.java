package soccer.game.streetsoccermanager.service_interfaces;



import soccer.game.streetsoccermanager.model.entities.Formation;

import java.util.List;

public interface IFormationService {
    List<Formation> getAll();
    Formation get(Long id);
    Boolean delete(Long id);
    Formation add(Formation formation);
    Formation update(Formation formation);
    void deleteAll();
}

