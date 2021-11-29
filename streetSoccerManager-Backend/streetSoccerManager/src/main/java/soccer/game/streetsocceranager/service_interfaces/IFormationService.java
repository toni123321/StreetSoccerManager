package soccer.game.streetsocceranager.service_interfaces;



import soccer.game.streetsocceranager.model.entities.Formation;

import java.util.List;

public interface IFormationService {
    List<Formation> getAll();
    Formation get(Long id);
    Boolean delete(Long id);
    Formation add(Formation formation);
    Formation update(Formation formation);
    void deleteAll();
}

