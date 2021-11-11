package soccer.game.streetSoccerManager.repository.PlayerStats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.model.entities.PlayerStats;
import soccer.game.streetSoccerManager.repository_interfaces.IPlayerStatsRepository;
import soccer.game.streetSoccerManager.repository_interfaces.jpa.IPlayerStatsJPARepository;

import java.util.List;

@Repository
public class PlayerStatsJPADatabase implements IPlayerStatsRepository {
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
    public PlayerStats add(PlayerStats stat) {
        if(stat.getId() == null) {
            return playerStatsRepo.save(stat);
        }
        return null;
    }

    @Override
    public PlayerStats update(PlayerStats stat) {
        if(stat.getId() != null) {
            return playerStatsRepo.save(stat);
        }
        return null;
    }

    @Override
    public void deleteAll() {
        playerStatsRepo.deleteAll();
    }
}
