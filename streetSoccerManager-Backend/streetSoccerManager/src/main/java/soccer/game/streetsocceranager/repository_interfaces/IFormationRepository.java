package soccer.game.streetsocceranager.repository_interfaces;

import soccer.game.streetsocceranager.model.entities.Formation;

import java.util.List;

public interface IFormationRepository {
    List<Formation> getAll();
    Formation get(Long id);
    Boolean delete(Long id);
    Formation add(Formation formation);
    Formation update(Formation formation);
    void deleteAll();
}
