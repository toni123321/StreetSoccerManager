package soccer.game.streetSoccerManager.repository.PlayerTeamInfo;

import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.model.entities.PlayerTeamInfo;
import soccer.game.streetSoccerManager.repository.Team.TeamStubDatabase;
import soccer.game.streetSoccerManager.repository_interfaces.IPlayerTeamInfoRepository;
import soccer.game.streetSoccerManager.repository_interfaces.ITeamRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PlayerTeamInfoStubDatabase implements IPlayerTeamInfoRepository {
    private List<PlayerTeamInfo> playersTeamInfo = new ArrayList<>();
    private ITeamRepository teamFakeDatabase = new TeamStubDatabase();

    public PlayerTeamInfoStubDatabase() {
        playersTeamInfo.add(new PlayerTeamInfo(1l, 1, teamFakeDatabase.get(0l)));
        playersTeamInfo.add(new PlayerTeamInfo(2l, 2, teamFakeDatabase.get(0l)));

    }

    @Override
    public List<PlayerTeamInfo> getAll() {
        return playersTeamInfo;
    }

    @Override
    public PlayerTeamInfo get(Long id) {
        for (PlayerTeamInfo playerTeamInfo : playersTeamInfo) {
            if (playerTeamInfo.getId().equals(id))
                return playerTeamInfo;
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        PlayerTeamInfo playerTeamInfo = get(id);
        if (playerTeamInfo == null){
            return false;
        }

        return playersTeamInfo.remove(playerTeamInfo);
    }

    @Override
    public Boolean add(PlayerTeamInfo playerTeamInfo) {
        if (this.get(playerTeamInfo.getId()) != null){
            return false;
        }
        playersTeamInfo.add(playerTeamInfo);
        return true;
    }

    @Override
    public Boolean update(PlayerTeamInfo playerTeamInfo) {
        PlayerTeamInfo oldPlayerTeamInfo = this.get(playerTeamInfo.getId());
        if (oldPlayerTeamInfo == null) {
            return false;
        }
        oldPlayerTeamInfo.setTeam(playerTeamInfo.getTeam());
        oldPlayerTeamInfo.setKitNr(playerTeamInfo.getKitNr());

        return true;
    }

    @Override
    public void deleteAll() {

    }
}
