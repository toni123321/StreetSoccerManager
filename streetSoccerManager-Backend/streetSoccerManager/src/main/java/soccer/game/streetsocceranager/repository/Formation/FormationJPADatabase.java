package soccer.game.streetsocceranager.repository.Formation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetsocceranager.model.entities.Formation;
import soccer.game.streetsocceranager.repository_interfaces.IFormationRepository;
import soccer.game.streetsocceranager.repository_interfaces.jpa.IFormationJPARepository;

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
        return formationRepo.findById(id).orElse(null);
    }

    @Override
    public Boolean delete(Long id) {
        formationRepo.deleteById(id);
        return true;
    }

    @Override
    public Formation add(Formation formation) {
        return formationRepo.save(formation);
    }

    @Override
    public Formation update(Formation formation) {
        return formationRepo.save(formation);
    }

    @Override
    public void deleteAll() {
        formationRepo.deleteAll();
    }
}
