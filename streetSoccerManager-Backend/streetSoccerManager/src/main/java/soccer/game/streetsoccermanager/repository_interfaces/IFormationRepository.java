package soccer.game.streetsoccermanager.repository_interfaces;

import soccer.game.streetsoccermanager.model.entities.Formation;

import java.util.List;

public interface IFormationRepository {
    List<Formation> getAll();
    Formation get(Long id);
    Boolean delete(Long id);
    Formation add(Formation formation);
    Formation update(Formation formation);
    void deleteAll();
}
