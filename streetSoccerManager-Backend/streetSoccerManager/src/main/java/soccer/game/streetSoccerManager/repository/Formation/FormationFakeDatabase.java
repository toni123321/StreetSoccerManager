package soccer.game.streetSoccerManager.repository.Formation;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.interfaces.repositoryInterfaces.IFormationRepository;
import soccer.game.streetSoccerManager.model.Formation;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FormationFakeDatabase implements IFormationRepository {

    @Getter
    private List<Formation> formations = new ArrayList<>();

    public FormationFakeDatabase() {
        formations.add(new Formation(1, "1-2-1"));
        formations.add(new Formation(2, "2-1-1"));
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
