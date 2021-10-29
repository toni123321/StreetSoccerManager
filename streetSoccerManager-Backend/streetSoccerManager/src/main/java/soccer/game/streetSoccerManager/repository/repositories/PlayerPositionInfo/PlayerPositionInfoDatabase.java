package soccer.game.streetSoccerManager.repository.repositories.PlayerPositionInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.model.entities.PlayerPositionInfo;
import soccer.game.streetSoccerManager.model.entities.PlayerStats;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPlayerPositionInfoRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa.IPlayerPositionInfoJPARepository;

import java.util.List;

@Repository
public class PlayerPositionInfoDatabase implements IPlayerPositionInfoRepository {
    @Autowired
    private IPlayerPositionInfoJPARepository playerPositionInfoRepo;


    @Override
    public List<PlayerPositionInfo> getAll() {
        return playerPositionInfoRepo.findAll();
    }

    @Override
    public PlayerPositionInfo get(Long id) {
        PlayerPositionInfo playerPositionInfo = playerPositionInfoRepo.findById(id).orElse(null);
        return playerPositionInfo;
    }

    @Override
    public Boolean delete(Long id) {
        if(get(id) != null) {
            playerPositionInfoRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean add(PlayerPositionInfo playerPositionInfo) {
        if(playerPositionInfo.getId() == null) {
            playerPositionInfoRepo.save(playerPositionInfo);
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(PlayerPositionInfo playerPositionInfo) {
        if(playerPositionInfo.getId() != null) {
            playerPositionInfoRepo.save(playerPositionInfo);
            return true;
        }
        return false;
    }
}
