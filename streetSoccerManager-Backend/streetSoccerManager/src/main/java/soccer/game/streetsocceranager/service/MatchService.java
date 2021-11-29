package soccer.game.streetsocceranager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetsocceranager.model.dtos.StartFriendlyMatchDTO;
import soccer.game.streetsocceranager.model.entities.FriendlyMatch;
import soccer.game.streetsocceranager.model.entities.Match;
import soccer.game.streetsocceranager.model.entities.Team;
import soccer.game.streetsocceranager.repository_interfaces.IMatchRepository;
import soccer.game.streetsocceranager.service_interfaces.IMatchService;

import java.util.List;

@Service
public class MatchService implements IMatchService {
    private IMatchRepository dataStore;

    public MatchService(@Qualifier("matchJPADatabase") IMatchRepository dataStore) {
        this.dataStore = dataStore;
    }


    @Override
    public List<Match> getAll() {
        return dataStore.getAll();
    }


    @Override
    public Match get(Long id) {
        return dataStore.get(id);
    }

    @Override
    public Boolean delete(Long id) {
        return dataStore.delete(id);
    }

    @Override
    public Match playFriendlyMatch(StartFriendlyMatchDTO startMatchInfo){
        // play friendly match algorithm
        FriendlyMatch friendlyMatch;
        String result = "";
        String statistic = "";

        Team homeTeam = startMatchInfo.getHomeTeam();
        Team awayTeam = startMatchInfo.getAwayTeam();
        if(homeTeam.getRating() > awayTeam.getRating()){
            result = "1:0";
            statistic = "Home team win";

        }
        else if(homeTeam.getRating() < awayTeam.getRating()){
            result = "0:1";
            statistic = "Away team win";
        }
        else
        {
            result = "0:0";
            statistic = "Draw";
        }
        friendlyMatch = new FriendlyMatch(homeTeam, awayTeam, result, statistic);

        return add(friendlyMatch);
    }

    // play match - boolean
    @Override
    public Match add(Match match) {
        return dataStore.add(match);
    }

    @Override
    public Match update(Match match) {
        return dataStore.update(match);
    }

}
