package soccer.game.streetSoccerManager.repository.repositories.Formation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.model.entities.Formation;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IFormationRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa.IFormationJPARepository;

import java.util.List;

@Repository
public class FormationDatabase implements IFormationRepository {

    @Autowired
    IFormationJPARepository formationRepo;

    @Override
    public List<Formation> getAll() {
        return formationRepo.findAll();
    }

    @Override
    public Formation get(Long id) {
        Formation formation = formationRepo.findById(id).orElse(null);
        return formation;
    }

    @Override
    public Boolean delete(Long id) {
        if(get(id) != null) {
            formationRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean add(Formation formation) {
        if(formation.getId() == null) {
            formationRepo.save(formation);
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(Formation formation) {
        if(formation.getId() != null) {
            formationRepo.save(formation);
            return true;
        }
        return false;
    }
}
