package soccer.game.streetSoccerManager.service;

import soccer.game.streetSoccerManager.model.Formation;
import soccer.game.streetSoccerManager.model.Team;
import soccer.game.streetSoccerManager.repository.FakeDatabase;

import java.util.List;

public class FormationService {
    private List<Formation> formations;
    private FakeDatabase fakeDatabase = new FakeDatabase();

    public FormationService() {
        this.formations = fakeDatabase.getFormations();
    }


    public List<Formation> getAllFormations() {
        return formations;
    }

    public Formation getFormation(int id) {
        for (Formation formation : formations) {
            if (formation.getId() == id)
                return formation;
        }
        return null;
    }

    public boolean deleteFormation(int id) {
        Formation formation = getFormation(id);
        if (formation == null){
            return false;
        }

        return formations.remove(formation);
    }


    public boolean addFormation(Formation formation) {
        if (this.getFormation(formation.getId()) != null){
            return false;
        }
        formations.add(formation);
        return true;
    }

    public boolean updateFormation(Formation formation) {
        Formation oldFormation = this.getFormation(formation.getId());
        if (oldFormation == null) {
            return false;
        }
        oldFormation.setName(formation.getName());
        return true;
    }
}

