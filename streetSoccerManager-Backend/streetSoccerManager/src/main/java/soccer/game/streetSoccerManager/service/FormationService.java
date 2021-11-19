package soccer.game.streetSoccerManager.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.repository_interfaces.IFormationRepository;
import soccer.game.streetSoccerManager.service_interfaces.IFormationService;
import soccer.game.streetSoccerManager.model.entities.Formation;

import java.util.List;

@Service
public class FormationService implements IFormationService {
    private IFormationRepository dataStore;
    private ModelMapper modelMapper = new ModelMapper();

    public FormationService(@Qualifier("formationJPADatabase") IFormationRepository dataStore) {
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
    public Formation add(Formation formation) {
        return dataStore.add(formation);
    }

    @Override
    public Formation update(Formation formation) {
        return dataStore.update(formation);
    }

    @Override
    public void deleteAll() {
        dataStore.deleteAll();
    }
}

