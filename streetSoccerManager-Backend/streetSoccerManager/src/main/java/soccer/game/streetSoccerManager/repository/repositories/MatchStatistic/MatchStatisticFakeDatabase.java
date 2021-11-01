package soccer.game.streetSoccerManager.repository.repositories.MatchStatistic;

import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.model.entities.MatchStatistic;
import soccer.game.streetSoccerManager.model.entities.Position;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IMatchStatisticRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MatchStatisticFakeDatabase implements IMatchStatisticRepository {
    private List<MatchStatistic> matchStatistics = new ArrayList<>();

    public MatchStatisticFakeDatabase() {
        matchStatistics.add(new MatchStatistic(1l, 1, 1, "text"));
        matchStatistics.add(new MatchStatistic(2l, 1, 2, "text"));
        matchStatistics.add(new MatchStatistic(3l, 1, 3, "text"));
    }

    @Override
    public List<MatchStatistic> getAll() {
        return matchStatistics;
    }

    @Override
    public MatchStatistic get(Long id) {
        for (MatchStatistic matchStatistic : matchStatistics) {
            if (matchStatistic.getId().equals(id))
                return matchStatistic;
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        MatchStatistic matchStatistic = get(id);
        if (matchStatistic == null){
            return false;
        }

        return matchStatistics.remove(matchStatistic);
    }

    @Override
    public Boolean add(MatchStatistic matchStatistic) {
        if (this.get(matchStatistic.getId()) != null){
            return false;
        }
        matchStatistics.add(matchStatistic);
        return true;
    }

    @Override
    public Boolean update(MatchStatistic matchStatistic) {
        MatchStatistic oldMatchStatistic = this.get(matchStatistic.getId());
        if (oldMatchStatistic == null) {
            return false;
        }
        oldMatchStatistic.setHomeTeamGoals(matchStatistic.getHomeTeamGoals());
        oldMatchStatistic.setAwayTeamGoals(matchStatistic.getAwayTeamGoals());
        oldMatchStatistic.setStatistic(matchStatistic.getStatistic());

        return true;
    }
}
