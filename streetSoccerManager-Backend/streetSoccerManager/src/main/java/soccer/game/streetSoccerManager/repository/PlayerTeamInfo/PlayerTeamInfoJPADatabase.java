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
    public Boolean add(PlayerTeamInfo playerTeamInfo) {
        if(playerTeamInfo.getId() == null) {
            playerTeamInfoJPARepository.save(playerTeamInfo);
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(PlayerTeamInfo playerTeamInfo) {
        if(playerTeamInfo.getId() != null) {
            playerTeamInfoJPARepository.save(playerTeamInfo);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAll() {
        playerTeamInfoJPARepository.deleteAll();
    }
}
