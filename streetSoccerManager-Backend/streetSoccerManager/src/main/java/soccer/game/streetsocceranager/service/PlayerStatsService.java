package soccer.game.streetsocceranager.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetsocceranager.model.entities.PlayerStats;
import soccer.game.streetsocceranager.repository_interfaces.IPlayerStatsRepository;
import soccer.game.streetsocceranager.service_interfaces.IPlayerStatsService;

import java.util.List;

@Service
public class PlayerStatsService implements IPlayerStatsService {
    private IPlayerStatsRepository dataStore;
    private ModelMapper modelMapper = new ModelMapper();

    public PlayerStatsService(@Qualifier("playerStatsJPADatabase")IPlayerStatsRepository dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public List<PlayerStats> getAll() {
        return dataStore.getAll();
    }

    @Override
    public PlayerStats get(Long id) {
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
    public PlayerStats add(PlayerStats stat) {
        if(stat.getId() == null) {
            return dataStore.add(stat);
        }
        return null;
    }

    @Override
    public PlayerStats update(PlayerStats stat) {
        if(stat.getId() != null) {
            return dataStore.update(stat);
        }
        return null;
    }

    @Override
    public void deleteAll() {
        dataStore.deleteAll();
    }

}
