package soccer.game.street_soccer_manager.repository.repositoryInterfaces;

import soccer.game.street_soccer_manager.model.Formation;

import java.util.List;

public interface IFormationRepository {
    List<Formation> getAll();
    Formation get(Long id);
    Boolean delete(Long id);
    Boolean add(Formation formation);
    Boolean update(Formation formation);
}
