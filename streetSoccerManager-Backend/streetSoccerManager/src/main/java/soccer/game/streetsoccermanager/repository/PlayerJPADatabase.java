package soccer.game.streetsoccermanager.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetsoccermanager.model.entities.Player;
import soccer.game.streetsoccermanager.repository_interfaces.IPlayerRepository;
import soccer.game.streetsoccermanager.repository_interfaces.jpa.IPlayerJPARepository;

import java.util.List;

@Repository
public class PlayerJPADatabase implements IPlayerRepository {

    private IPlayerJPARepository playerRepo;

    @Autowired
    public PlayerJPADatabase(IPlayerJPARepository playerRepo) {
        this.playerRepo = playerRepo;
    }

    @Override
    public List<Player> getAll() {
        return playerRepo.findAll();
    }

    @Override
    public Player get(Long id) {
        return playerRepo.findById(id).orElse(null);
    }

    @Override
    public Boolean delete(Long id) {
        playerRepo.deleteById(id);
        return true;
    }

    @Override
    public Player add(Player player) {
        return playerRepo.save(player);
    }

    @Override
    public Player update(Player player) {
        return playerRepo.save(player);
    }

    @Override
    public void deleteAll() {
        playerRepo.deleteAll();
    }
}
