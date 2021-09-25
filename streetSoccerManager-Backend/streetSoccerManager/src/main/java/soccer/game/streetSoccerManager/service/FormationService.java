package soccer.game.streetSoccerManager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.interfaces.repositoryInterfaces.IFormationRepository;
import soccer.game.streetSoccerManager.interfaces.serviceInterfaces.IFormationService;
import soccer.game.streetSoccerManager.model.Formation;

import java.util.List;

@Service
public class FormationService implements IFormationService {

    @Qualifier("formationFakeDatabase")
    private IFormationRepository dataStore;

    public FormationService( IFormationRepository dataStore) {
        this.dataStore = dataStore;
    }


    @Override
    public List<Formation> getAll() {
        return dataStore.getAll();
    }

    @Override
    public Formation get(int id) {
        return dataStore.get(id);
    }

    @Override
    public Boolean delete(int id) {
        return dataStore.delete(id);
    }


    @Override
    public Boolean add(Formation formation) {
        return dataStore.add(formation);
    }

    @Override
    public Boolean update(Formation formation) {
        return dataStore.update(formation);
    }
}

