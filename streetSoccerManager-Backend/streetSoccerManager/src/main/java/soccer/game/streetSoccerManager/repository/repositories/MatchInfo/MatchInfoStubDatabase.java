package soccer.game.streetSoccerManager.repository.repositories.MatchInfo;

import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.model.entities.MatchInfo;
import soccer.game.streetSoccerManager.repository.repositories.Team.TeamStubDatabase;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IMatchInfoRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.ITeamRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MatchInfoStubDatabase implements IMatchInfoRepository {
    private List<MatchInfo> matchInfos = new ArrayList<>();
    private ITeamRepository teamFakeDatabase = new TeamStubDatabase();

    public MatchInfoStubDatabase() {
        matchInfos.add(new MatchInfo(1l, teamFakeDatabase.get(0l), teamFakeDatabase.get(1l)));
        matchInfos.add(new MatchInfo(2l, teamFakeDatabase.get(0l), teamFakeDatabase.get(2l)));
        matchInfos.add(new MatchInfo(3l, teamFakeDatabase.get(0l), teamFakeDatabase.get(3l)));
    }


    @Override
    public List<MatchInfo> getAll() {
        return matchInfos;
    }

    @Override
    public MatchInfo get(Long id) {
        for (MatchInfo matchInfo : matchInfos) {
            if (matchInfo.getId().equals(id))
                return matchInfo;
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        MatchInfo matchInfo = get(id);
        if (matchInfo == null){
            return false;
        }
        return matchInfos.remove(matchInfo);
    }

    @Override
    public Boolean add(MatchInfo matchInfo) {
        if (this.get(matchInfo.getId()) != null){
            return false;
        }
        matchInfos.add(matchInfo);
        return true;
    }

    @Override
    public Boolean update(MatchInfo matchInfo) {
        MatchInfo oldMatchInfo = this.get(matchInfo.getId());
        if (oldMatchInfo == null) {
            return false;
        }
        oldMatchInfo.setHomeTeam(matchInfo.getHomeTeam());
        oldMatchInfo.setAwayTeam(matchInfo.getAwayTeam());

        return true;
    }
}
