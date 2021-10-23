package soccer.game.streetSoccerManager.repository.repositories.PlayerStats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.model.PlayerStats;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPlayerStatsRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa.IPlayerStatsJPARepository;

import java.util.List;

@Repository
public class PlayerStatsDatabase implements IPlayerStatsRepository {
    @Autowired
    IPlayerStatsJPARepository repo;

    @Override
    public List<PlayerStats> getAll() {
        return repo.findAll();
    }

    @Override
    public PlayerStats get(Long id) {
        if(repo.findById(id).isPresent()){
            return repo.findById(id).get();
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        if(get(id) != null) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean add(PlayerStats stat) {
        if(stat.getId() == null) {
            repo.save(stat);
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(PlayerStats stat) {
        if(stat.getId() != null) {
            repo.save(stat);
            return true;
        }
        return false;
    }
}
