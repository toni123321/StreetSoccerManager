package soccer.game.streetsoccermanager.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetsoccermanager.model.entities.Player;
import soccer.game.streetsoccermanager.repository_interfaces.IPlayerRepository;
import soccer.game.streetsoccermanager.repository_interfaces.jpa.IPlayerJPARepository;

import java.util.List;

@Repository
public class PlayerJPADatabase implements IPlayerRepository {
    @Autowired
    IPlayerJPARepository playerRepo;

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
        if(get(id) != null) {
            playerRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Player add(Player player) {
        if(player.getId() == null) {
            return playerRepo.save(player);
        }
        return null;
    }

    @Override
    public Player update(Player player) {
        if(player.getId() != null) {
            return playerRepo.save(player);
        }
        return null;
    }

    @Override
    public void deleteAll() {
        playerRepo.deleteAll();
    }
}
