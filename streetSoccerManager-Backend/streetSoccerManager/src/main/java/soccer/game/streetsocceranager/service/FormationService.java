package soccer.game.streetsocceranager.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetsocceranager.repository_interfaces.IFormationRepository;
import soccer.game.streetsocceranager.service_interfaces.IFormationService;
import soccer.game.streetsocceranager.model.entities.Formation;

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
        if(get(id) != null) {
            dataStore.delete(id);
            return true;
        }
        return false;
    }


    @Override
    public Formation add(Formation formation) {
        if(formation.getId() == null) {
            return dataStore.add(formation);
        }
        return null;
    }

    @Override
    public Formation update(Formation formation) {
        if(formation.getId() != null) {
            return dataStore.update(formation);
        }
        return null;
    }

    @Override
    public void deleteAll() {
        dataStore.deleteAll();
    }
}

