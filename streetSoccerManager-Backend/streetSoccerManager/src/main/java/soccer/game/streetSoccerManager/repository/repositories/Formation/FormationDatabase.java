package soccer.game.streetSoccerManager.repository.repositories.Formation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.model.Formation;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IFormationRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa.IFormationJPARepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa.IUserJPARepository;

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
        if(repo.findById(id).isPresent()){
            return repo.findById(id).get();
        }
        return null;
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
