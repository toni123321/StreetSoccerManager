package soccer.game.streetSoccerManager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.Interfaces.IRepository;
import soccer.game.streetSoccerManager.Interfaces.IService;
import soccer.game.streetSoccerManager.model.Formation;
import soccer.game.streetSoccerManager.model.Team;
import soccer.game.streetSoccerManager.repository.FakeDatabase;

import java.util.List;

@Service
public class FormationService implements IService {
    private List<Formation> formations;



    private IRepository fakeDatabase;

    public FormationService(@Qualifier("formationFakeDatabase") IRepository fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
        this.formations = (List<Formation>) fakeDatabase.getAll();
    }


    @Override
    public List<Formation> getAll() {
        return formations;
    }

    @Override
    public Formation get(Object id) {
        for (Formation formation : formations) {
            if (formation.getId() == (int) id)
                return formation;
        }
        return null;
    }

    @Override
    public Boolean delete(Object id) {
        Formation formation = get(id);
        if (formation == null){
            return false;
        }

        return formations.remove(formation);
    }


    @Override
    public Boolean add(Object formation) {
        if (this.get(((Formation) formation).getId()) != null){
            return false;
        }
        formations.add(((Formation) formation));
        return true;
    }

    @Override
    public Boolean update(Object formation) {
        Formation oldFormation = this.get(((Formation) formation).getId());
        if (oldFormation == null) {
            return false;
        }
        oldFormation.setName(((Formation) formation).getName());
        return true;
    }
}

