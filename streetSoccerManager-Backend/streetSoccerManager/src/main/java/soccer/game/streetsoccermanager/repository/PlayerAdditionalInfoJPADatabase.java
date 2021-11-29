package soccer.game.streetsoccermanager.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetsoccermanager.model.entities.PlayerAdditionalInfo;
import soccer.game.streetsoccermanager.repository_interfaces.IPlayerAdditionalInfoRepository;
import soccer.game.streetsoccermanager.repository_interfaces.jpa.IPlayerAdditionalInfoJPARepository;

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
        return playerAdditionalInfoRepo.findById(id).orElse(null);
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
    public PlayerAdditionalInfo add(PlayerAdditionalInfo playerAdditionalInfo) {
        if(playerAdditionalInfo.getId() == null) {
            return playerAdditionalInfoRepo.save(playerAdditionalInfo);
        }
        return null;
    }

    @Override
    public PlayerAdditionalInfo update(PlayerAdditionalInfo playerAdditionalInfo) {
        if(playerAdditionalInfo.getId() != null) {
            return playerAdditionalInfoRepo.save(playerAdditionalInfo);
        }
        return null;
    }

    @Override
    public void deleteAll() {
        playerAdditionalInfoRepo.deleteAll();
    }
}
