package soccer.game.streetSoccerManager.repository.repositories.PlayerPersonalInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.model.entities.Player;
import soccer.game.streetSoccerManager.model.entities.PlayerPersonalInfo;
import soccer.game.streetSoccerManager.model.entities.Position;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPlayerPersonalInfoRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa.IPlayerPersonalInfoJPARepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa.IPositionJPARepository;

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
    public Boolean add(PlayerPersonalInfo playerPersonalInfo) {
        if(playerPersonalInfo.getId() == null) {
            playerPersonalInfoJPARepo.save(playerPersonalInfo);
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(PlayerPersonalInfo playerPersonalInfo) {
        if(playerPersonalInfo.getId() != null) {
            playerPersonalInfoJPARepo.save(playerPersonalInfo);
            return true;
        }
        return false;
    }
}
