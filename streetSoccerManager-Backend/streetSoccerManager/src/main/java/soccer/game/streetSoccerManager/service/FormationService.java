package soccer.game.streetSoccerManager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.interfaces.repositoryInterfaces.IFormationRepository;
import soccer.game.streetSoccerManager.interfaces.serviceInterfaces.IFormationService;
import soccer.game.streetSoccerManager.model.Formation;

import java.util.List;

@Service
public class FormationService implements IFormationService {
    private List<Formation> formations;


    private IFormationRepository fakeDatabase;

    public FormationService(@Qualifier("formationFakeDatabase") IFormationRepository fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
        this.formations = fakeDatabase.getAll();
    }


    @Override
    public List<Formation> getAll() {
        return formations;
    }

    @Override
    public Formation get(int id) {
        for (Formation formation : formations) {
            if (formation.getId() == id)
                return formation;
        }
        return null;
    }

    @Override
    public Boolean delete(int id) {
        Formation formation = get(id);
        if (formation == null){
            return false;
        }
        return formations.remove(formation);
    }


    @Override
    public Boolean add(Formation formation) {
        if (this.get(formation.getId()) != null){
            return false;
        }
        formations.add(formation);
        return true;
    }

    @Override
    public Boolean update(Formation formation) {
        Formation oldFormation = this.get(formation.getId());
        if (oldFormation == null) {
            return false;
        }
        oldFormation.setName(formation.getName());
        return true;
    }
}

