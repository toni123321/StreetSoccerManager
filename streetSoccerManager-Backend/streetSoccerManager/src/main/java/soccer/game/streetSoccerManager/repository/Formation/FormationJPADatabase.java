package soccer.game.streetSoccerManager.repository.Formation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.model.entities.Formation;
import soccer.game.streetSoccerManager.repository_interfaces.IFormationRepository;
import soccer.game.streetSoccerManager.repository_interfaces.jpa.IFormationJPARepository;

import java.util.List;

@Repository
public class FormationJPADatabase implements IFormationRepository {

    @Autowired
    IFormationJPARepository formationRepo;


    @Override
    public List<Formation> getAll() {
        return formationRepo.findAll();
    }

    @Override
    public Formation get(Long id) {
        if(formationRepo.findById(id).isEmpty())
        {
            return null;
        }
        return formationRepo.findById(id).orElse(null);
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
    public Formation add(Formation formation) {
        if(formation.getId() == null) {
            return formationRepo.save(formation);
        }
        return null;
    }

    @Override
    public Formation update(Formation formation) {
        if(formation.getId() != null) {
            return formationRepo.save(formation);
        }
        return null;
    }

    @Override
    public void deleteAll() {
        formationRepo.deleteAll();
    }
}
