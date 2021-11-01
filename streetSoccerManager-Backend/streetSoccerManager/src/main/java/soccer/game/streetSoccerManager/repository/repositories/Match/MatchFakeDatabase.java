package soccer.game.streetSoccerManager.repository.repositories.Match;

import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.model.entities.FriendlyMatch;
import soccer.game.streetSoccerManager.model.entities.Match;
import soccer.game.streetSoccerManager.model.entities.MatchInfo;
import soccer.game.streetSoccerManager.repository.repositories.MatchInfo.MatchInfoFakeDatabase;
import soccer.game.streetSoccerManager.repository.repositories.MatchStatistic.MatchStatisticFakeDatabase;
import soccer.game.streetSoccerManager.repository.repositories.Team.TeamFakeDatabase;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IMatchInfoRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IMatchRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IMatchStatisticRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.ITeamRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MatchFakeDatabase implements IMatchRepository {
    private List<Match> matches = new ArrayList<>();
    private IMatchInfoRepository matchInfoFakeDatabase = new MatchInfoFakeDatabase();
    private IMatchStatisticRepository matchStatisticFakeDatabase = new MatchStatisticFakeDatabase();

    public MatchFakeDatabase() {
        matches.add(new FriendlyMatch(1l, matchInfoFakeDatabase.get(1l), matchStatisticFakeDatabase.get(1l)));
        matches.add(new FriendlyMatch(2l, matchInfoFakeDatabase.get(2l), matchStatisticFakeDatabase.get(2l)));
    }

    @Override
    public List<Match> getAll() {
        return matches;
    }

    @Override
    public Match get(Long id) {
        for (Match match : matches) {
            if (match.getId().equals(id))
                return match;
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        Match match = get(id);
        if (match == null){
            return false;
        }
        return matches.remove(match);
    }

    @Override
    public Boolean add(Match match) {
        if (this.get(match.getId()) != null){
            return false;
        }
        matches.add(match);
        return true;
    }

    @Override
    public Boolean update(Match match) {
        Match oldMatch = this.get(match.getId());
        if (oldMatch == null) {
            return false;
        }
        oldMatch.setMatchInfo(match.getMatchInfo());
        oldMatch.setMatchStatistic(match.getMatchStatistic());

        return true;
    }
}
