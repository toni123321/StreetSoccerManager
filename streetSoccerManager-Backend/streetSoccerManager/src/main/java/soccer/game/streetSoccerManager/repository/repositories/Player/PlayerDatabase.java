package soccer.game.streetSoccerManager.repository.repositories.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.model.entities.Player;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPlayerRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa.IPlayerJPARepository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PlayerDatabase implements IPlayerRepository {
    @Autowired
    IPlayerJPARepository playerRepo;

    @Override
    public List<Player> getAll() {
        return playerRepo.findAll();
    }

    @Override
    public Player get(Long id) {
        Player player = playerRepo.findById(id).orElse(null);
        return player;
    }

    @Override
    public Boolean delete(Long id) {
        if(get(id) != null) {
            playerRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean add(Player player) {
        if(player.getId() == null) {
            playerRepo.save(player);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean update(Player player) {
        if(player.getId() != null) {
            playerRepo.save(player);
            return true;
        }
        return false;
    }
}
