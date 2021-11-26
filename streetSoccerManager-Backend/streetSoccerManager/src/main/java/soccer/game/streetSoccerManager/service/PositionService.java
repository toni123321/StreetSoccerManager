package soccer.game.streetSoccerManager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.model.entities.Position;
import soccer.game.streetSoccerManager.repository_interfaces.IPositionRepository;
import soccer.game.streetSoccerManager.service_interfaces.IPositionService;

import java.util.List;

@Service
public class PositionService implements IPositionService {

    private IPositionRepository dataStore;

    public PositionService(@Qualifier("positionJPADatabase") IPositionRepository dataStore) {
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
