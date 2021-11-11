package soccer.game.streetSoccerManager.repository.PlayerTeamInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.model.entities.PlayerTeamInfo;
import soccer.game.streetSoccerManager.repository_interfaces.IPlayerTeamInfoRepository;
import soccer.game.streetSoccerManager.repository_interfaces.jpa.IPlayerTeamInfoJPARepository;

import java.util.List;

@Repository
public class PlayerTeamInfoJPADatabase implements IPlayerTeamInfoRepository {
    @Autowired
    IPlayerTeamInfoJPARepository playerTeamInfoJPARepository;

    @Override
    public List<PlayerTeamInfo> getAll() {
        return playerTeamInfoJPARepository.findAll();
    }

    @Override
    public PlayerTeamInfo get(Long id) {
        PlayerTeamInfo playerTeamInfo = playerTeamInfoJPARepository.findById(id).orElse(null);
        return playerTeamInfo;
    }

    @Override
    public Boolean delete(Long id) {
        if(get(id) != null) {
            playerTeamInfoJPARepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public PlayerTeamInfo add(PlayerTeamInfo playerTeamInfo) {
        if(playerTeamInfo.getId() == null) {
            return playerTeamInfoJPARepository.save(playerTeamInfo);
        }
        return null;
    }

    @Override
    public PlayerTeamInfo update(PlayerTeamInfo playerTeamInfo) {
        if(playerTeamInfo.getId() != null) {
            return playerTeamInfoJPARepository.save(playerTeamInfo);
        }
        return null;
    }

    @Override
    public void deleteAll() {
        playerTeamInfoJPARepository.deleteAll();
    }
}
