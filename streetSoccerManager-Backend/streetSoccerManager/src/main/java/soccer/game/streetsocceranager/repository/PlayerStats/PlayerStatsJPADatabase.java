package soccer.game.streetsocceranager.repository.PlayerStats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetsocceranager.model.entities.PlayerStats;
import soccer.game.streetsocceranager.repository_interfaces.IPlayerStatsRepository;
import soccer.game.streetsocceranager.repository_interfaces.jpa.IPlayerStatsJPARepository;

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
        return playerStatsRepo.findById(id).orElse(null);
    }

    @Override
    public Boolean delete(Long id) {
            playerStatsRepo.deleteById(id);
            return true;
    }

    @Override
    public PlayerStats add(PlayerStats stat) {
        return playerStatsRepo.save(stat);
    }

    @Override
    public PlayerStats update(PlayerStats stat) {
        return playerStatsRepo.save(stat);
    }

    @Override
    public void deleteAll() {
        playerStatsRepo.deleteAll();
    }
}
