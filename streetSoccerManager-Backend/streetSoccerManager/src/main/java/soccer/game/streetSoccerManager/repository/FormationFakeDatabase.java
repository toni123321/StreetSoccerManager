package soccer.game.streetSoccerManager.repository;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.Interfaces.IRepository;
import soccer.game.streetSoccerManager.model.Formation;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FormationFakeDatabase implements IRepository {

    @Getter
    private List<Formation> formations = new ArrayList<>();

    public FormationFakeDatabase() {
        formations.add(new Formation(1, "1-2-1"));
        formations.add(new Formation(2, "2-1-1"));
    }

    @Override
    public Object getAll() {
        return formations;
    }

    @Override
    public Object get(Object obj) {
        return null;
    }

    @Override
    public Object delete(Object obj) {
        return null;
    }

    @Override
    public Object add(Object obj) {
        return null;
    }

    @Override
    public Object update(Object obj) {
        return null;
    }
}
