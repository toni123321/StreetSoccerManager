package soccer.game.street_soccer_manager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.street_soccer_manager.repository.repositoryInterfaces.IFormationRepository;
import soccer.game.street_soccer_manager.service.serviceInterfaces.IFormationService;
import soccer.game.street_soccer_manager.model.Formation;

import java.util.List;

@Service
public class FormationService implements IFormationService {


    private IFormationRepository dataStore;

    public FormationService(@Qualifier("formationDatabase") IFormationRepository dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public List<Formation> getAll() {
        return dataStore.getAll();
    }

    @Override
    public Formation get(Long id) {
        return dataStore.get(id);
    }

    @Override
    public Boolean delete(Long id) {
        return dataStore.delete(id);
    }


    @Override
    public Boolean add(Formation formation) {
        //formation.setId(Long.valueOf(dataStore.getAll().size()));
        return dataStore.add(formation);
    }

    @Override
    public Boolean update(Formation formation) {
        return dataStore.update(formation);
    }
}
