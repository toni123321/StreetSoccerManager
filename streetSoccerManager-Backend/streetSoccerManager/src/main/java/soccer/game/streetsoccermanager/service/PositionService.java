package soccer.game.streetsoccermanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetsoccermanager.model.entities.Position;
import soccer.game.streetsoccermanager.repository_interfaces.IPositionRepository;
import soccer.game.streetsoccermanager.service_interfaces.IPositionService;

import java.util.List;

@Service
public class PositionService implements IPositionService {

    private IPositionRepository dataStore;

    @Autowired
    public PositionService(IPositionRepository dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public List<Position> getAll() {
        return dataStore.getAll();
    }

    @Override
    public Position get(Long id) {
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
    public Position add(Position position) {
        if(position.getId() == null) {
            return dataStore.add(position);
        }
        return null;
    }

    @Override
    public Position update(Position position) {
        if(position.getId() != null) {
            return dataStore.update(position);
        }
        return null;
    }

    @Override
    public void deleteAll() {
        dataStore.deleteAll();
    }

}
