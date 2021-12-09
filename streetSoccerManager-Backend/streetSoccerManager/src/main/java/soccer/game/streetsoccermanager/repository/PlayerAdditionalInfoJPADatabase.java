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
        playerAdditionalInfoRepo.deleteById(id);
        return true;
    }

    @Override
    public PlayerAdditionalInfo add(PlayerAdditionalInfo playerAdditionalInfo) {
        return playerAdditionalInfoRepo.save(playerAdditionalInfo);
    }

    @Override
    public PlayerAdditionalInfo update(PlayerAdditionalInfo playerAdditionalInfo) {
        return playerAdditionalInfoRepo.save(playerAdditionalInfo);

    }

    @Override
    public void deleteAll() {
        playerAdditionalInfoRepo.deleteAll();
    }
}
