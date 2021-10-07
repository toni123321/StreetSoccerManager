package soccer.game.streetSoccerManager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.interfaces.repositoryInterfaces.IFormationPositionRepository;
import soccer.game.streetSoccerManager.interfaces.repositoryInterfaces.IFormationRepository;
import soccer.game.streetSoccerManager.interfaces.serviceInterfaces.IFormationPositionService;
import soccer.game.streetSoccerManager.model.FormationPosition;

import java.util.List;

@Service
public class FormationPositionService implements IFormationPositionService {

    @Qualifier("formationPositionFakeDatabase")
    private IFormationPositionRepository dataStore;

    public FormationPositionService( IFormationPositionRepository dataStore) {
        this.dataStore = dataStore;
    }


    @Override
    public List<FormationPosition> getAllPositionsByTeamAndFormation(int teamId, int formationId){
        return dataStore.getAllPositionsByTeamAndFormation(teamId, formationId);
    }

    @Override
    public List<FormationPosition> getAll() {
        return dataStore.getAll();
    }

    @Override
    public FormationPosition get(int id) {
        return dataStore.get(id);
    }

    @Override
    public Boolean delete(int id) {
        return dataStore.delete(id);
    }

    @Override
    public Boolean add(FormationPosition position) {
        return dataStore.add(position);
    }

    @Override
    public Boolean update(FormationPosition position) {
        return dataStore.update(position);
    }
}
