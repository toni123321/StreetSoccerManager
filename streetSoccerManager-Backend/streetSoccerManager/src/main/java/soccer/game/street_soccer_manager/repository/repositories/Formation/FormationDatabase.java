package soccer.game.street_soccer_manager.repository.repositories.Formation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.street_soccer_manager.model.Formation;
import soccer.game.street_soccer_manager.repository.repositoryInterfaces.IFormationRepository;
import soccer.game.street_soccer_manager.repository.repositoryInterfaces.jpa.IFormationJPARepository;

import java.util.List;

@Repository
public class FormationDatabase implements IFormationRepository {

    @Autowired
    IFormationJPARepository repo;

    @Override
    public List<Formation> getAll() {
        return repo.findAll();
    }

    @Override
    public Formation get(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public Boolean delete(Long id) {
        if(get(id) != null) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean add(Formation formation) {
        if(formation.getId() == null) {
            repo.save(formation);
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(Formation formation) {
        if(formation.getId() != null) {
            repo.save(formation);
            return true;
        }
        return false;
    }
}
