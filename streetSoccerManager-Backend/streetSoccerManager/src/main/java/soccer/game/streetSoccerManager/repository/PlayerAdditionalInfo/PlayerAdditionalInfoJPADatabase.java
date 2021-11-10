package soccer.game.streetSoccerManager.repository.PlayerAdditionalInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.model.entities.PlayerAdditionalInfo;
import soccer.game.streetSoccerManager.repository_interfaces.IPlayerAdditionalInfoRepository;
import soccer.game.streetSoccerManager.repository_interfaces.jpa.IPlayerAdditionalInfoJPARepository;

import java.util.List;

@Repository
public class PlayerAdditionalInfoJPADatabase implements IPlayerAdditionalInfoRepository{
    @Autowired
    IPlayerAdditionalInfoJPARepository playerAdditionalInfoRepo;

    @Override
    public List<PlayerAdditionalInfo> getAll() {
        return playerAdditionalInfoRepo.findAll();
    }

    @Override
    public PlayerAdditionalInfo get(Long id) {
        PlayerAdditionalInfo playerAdditionalInfo = playerAdditionalInfoRepo.findById(id).orElse(null);
        return playerAdditionalInfo;
    }

    @Override
    public Boolean delete(Long id) {
        if(get(id) != null) {
            PlayerAdditionalInfo playerAdditionalInfo = get(id);
            playerAdditionalInfo.setPlayer(null);
            playerAdditionalInfoRepo.deleteById(id);

            return true;
        }
        return false;
    }

    @Override
    public Boolean add(PlayerAdditionalInfo playerAdditionalInfo) {
        if(playerAdditionalInfo.getId() == null) {
            playerAdditionalInfoRepo.save(playerAdditionalInfo);
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(PlayerAdditionalInfo playerAdditionalInfo) {
        if(playerAdditionalInfo.getId() != null) {
            playerAdditionalInfoRepo.save(playerAdditionalInfo);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAll() {
        playerAdditionalInfoRepo.deleteAll();
    }
}
