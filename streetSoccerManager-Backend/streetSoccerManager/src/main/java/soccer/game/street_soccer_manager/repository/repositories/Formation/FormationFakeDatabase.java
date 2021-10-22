package soccer.game.street_soccer_manager.repository.repositories.Formation;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import soccer.game.street_soccer_manager.repository.repositoryInterfaces.IFormationRepository;
import soccer.game.street_soccer_manager.model.Formation;

import java.util.ArrayList;
import java.util.List;

@Repository
@Getter
public class FormationFakeDatabase implements IFormationRepository {


    private List<Formation> formations = new ArrayList<>();

    public FormationFakeDatabase() {
        formations.add(new Formation(1l, "1-2-1"));
        formations.add(new Formation(2l, "2-1-1"));
    }


    @Override
    public List<Formation> getAll() {
        return formations;
    }

    @Override
    public Formation get(Long id) {
        for (Formation formation : formations) {
            if (formation.getId().equals(id))
                return formation;
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        Formation formation = get(id);
        if (formation == null){
            return false;
        }
        return formations.remove(formation);
    }

    @Override
    public Boolean add(Formation formation) {
        if(formation.getId() == null) {
            formation.setId(Long.valueOf(formations.size()));
        }
        else if (this.get(formation.getId()) != null){
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
