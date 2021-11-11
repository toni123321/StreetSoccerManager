package soccer.game.streetSoccerManager.repository.PlayerPersonalInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.model.entities.PlayerPersonalInfo;
import soccer.game.streetSoccerManager.repository_interfaces.IPlayerPersonalInfoRepository;
import soccer.game.streetSoccerManager.repository_interfaces.jpa.IPlayerPersonalInfoJPARepository;

import java.util.List;

@Repository
public class PlayerPersonalInfoJPADatabase implements IPlayerPersonalInfoRepository {
    @Autowired
    IPlayerPersonalInfoJPARepository playerPersonalInfoJPARepo;

    @Override
    public List<PlayerPersonalInfo> getAll() {
        return playerPersonalInfoJPARepo.findAll();
    }

    @Override
    public PlayerPersonalInfo get(Long id) {
        PlayerPersonalInfo playerPersonalInfo = playerPersonalInfoJPARepo.findById(id).orElse(null);
        return playerPersonalInfo;
    }

    @Override
    public Boolean delete(Long id) {
        if(get(id) != null) {
            playerPersonalInfoJPARepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public PlayerPersonalInfo add(PlayerPersonalInfo playerPersonalInfo) {
        if(playerPersonalInfo.getId() == null) {
            return playerPersonalInfoJPARepo.save(playerPersonalInfo);
        }
        return null;
    }

    @Override
    public PlayerPersonalInfo update(PlayerPersonalInfo playerPersonalInfo) {
        if(playerPersonalInfo.getId() != null) {
            return playerPersonalInfoJPARepo.save(playerPersonalInfo);
        }
        return null;
    }

    @Override
    public void deleteAll() {
        playerPersonalInfoJPARepo.deleteAll();
    }
}
