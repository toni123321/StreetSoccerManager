package soccer.game.streetSoccerManager.repository.PlayerPositionInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.model.entities.PlayerPositionInfo;
import soccer.game.streetSoccerManager.repository_interfaces.IPlayerPositionInfoRepository;
import soccer.game.streetSoccerManager.repository_interfaces.jpa.IPlayerPositionInfoJPARepository;

import java.util.List;

@Repository
public class PlayerPositionInfoJPADatabase implements IPlayerPositionInfoRepository {
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
    public PlayerPositionInfo add(PlayerPositionInfo playerPositionInfo) {
        if(playerPositionInfo.getId() == null) {
            return playerPositionInfoRepo.save(playerPositionInfo);
        }
        return null;
    }

    @Override
    public PlayerPositionInfo update(PlayerPositionInfo playerPositionInfo) {
        if(playerPositionInfo.getId() != null) {
            return playerPositionInfoRepo.save(playerPositionInfo);
        }
        return null;
    }

    @Override
    public void deleteAll() {
        playerPositionInfoRepo.deleteAll();
    }
}
