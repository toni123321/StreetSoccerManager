package soccer.game.streetsoccermanager.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetsoccermanager.model.entities.PlayerTeamInfo;
import soccer.game.streetsoccermanager.repository_interfaces.IPlayerTeamInfoRepository;
import soccer.game.streetsoccermanager.repository_interfaces.jpa.IPlayerTeamInfoJPARepository;

import java.util.List;

@Repository
public class PlayerTeamInfoJPADatabase implements IPlayerTeamInfoRepository {

    private IPlayerTeamInfoJPARepository playerTeamInfoJPARepository;

    @Autowired
    public PlayerTeamInfoJPADatabase(IPlayerTeamInfoJPARepository playerTeamInfoJPARepository) {
        this.playerTeamInfoJPARepository = playerTeamInfoJPARepository;
    }

    @Override
    public List<PlayerTeamInfo> getAll() {
        return playerTeamInfoJPARepository.findAll();
    }

    @Override
    public PlayerTeamInfo get(Long id) {
        return playerTeamInfoJPARepository.findById(id).orElse(null);
    }

    @Override
    public Boolean delete(Long id) {
        playerTeamInfoJPARepository.deleteById(id);
        return true;
    }

    @Override
    public PlayerTeamInfo add(PlayerTeamInfo playerTeamInfo) {
        return playerTeamInfoJPARepository.save(playerTeamInfo);
    }

    @Override
    public PlayerTeamInfo update(PlayerTeamInfo playerTeamInfo) {
        return playerTeamInfoJPARepository.save(playerTeamInfo);
    }

    @Override
    public void deleteAll() {
        playerTeamInfoJPARepository.deleteAll();
    }
}
