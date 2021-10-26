package soccer.game.streetSoccerManager.repository.repositories.PlayerStats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.model.entities.PlayerStats;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPlayerStatsRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa.IPlayerStatsJPARepository;

import java.util.List;

@Repository
public class PlayerStatsDatabase implements IPlayerStatsRepository {
    @Autowired
    IPlayerStatsJPARepository playerStatsRepo;

    @Override
    public List<PlayerStats> getAll() {
        return playerStatsRepo.findAll();
    }

    @Override
    public PlayerStats get(Long id) {
        PlayerStats playerStats = playerStatsRepo.findById(id).orElse(null);
        return playerStats;
    }

    @Override
    public Boolean delete(Long id) {
        if(get(id) != null) {
            playerStatsRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean add(PlayerStats stat) {
        if(stat.getId() == null) {
            playerStatsRepo.save(stat);
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(PlayerStats stat) {
        if(stat.getId() != null) {
            playerStatsRepo.save(stat);
            return true;
        }
        return false;
    }
}
