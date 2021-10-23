package soccer.game.streetSoccerManager.repository.repositories.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.model.Player;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPlayerRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa.IPlayerJPARepository;

import java.util.List;

@Repository
public class PlayerDatabase implements IPlayerRepository {
    @Autowired
    IPlayerJPARepository repo;

    @Override
    public List<Player> getAll() {
        return repo.findAll();
    }

    @Override
    public Player get(Long id) {
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
    public Boolean add(Player player) {
        if(player.getId() == null) {
            repo.save(player);
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(Player player) {
        if(player.getId() != null) {
            repo.save(player);
            return true;
        }
        return false;
    }
}
