package soccer.game.streetsoccermanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soccer.game.streetsoccermanager.model.entities.CustomTeam;
import soccer.game.streetsoccermanager.model.entities.Match;
import soccer.game.streetsoccermanager.repository_interfaces.IMatchRepository;
import soccer.game.streetsoccermanager.service_interfaces.IMatchService;
import soccer.game.streetsoccermanager.service_interfaces.ITeamService;

import java.util.List;

@Service
public class MatchService implements IMatchService {
    private IMatchRepository dataStore;

    private ITeamService teamService;
    @Autowired
    public MatchService(IMatchRepository dataStore, ITeamService teamService) {
        this.dataStore = dataStore;
        this.teamService = teamService;
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
        if(get(id) != null) {
            dataStore.delete(id);
            return true;
        }
        return false;
    }

    @Override
    public Match add(Match match) {
        if(match.getId() == null) {
            match.setResult("0:0");
            match.setStatistic("Match started");
            return dataStore.add(match);
        }
        return null;
    }

    @Override
    public Match update(Match match) {
        if(match.getId() != null) {
            return dataStore.update(match);
        }
        return null;
    }

    private Boolean isUserTeamHome(Match match){
        return teamService.get(match.getHomeTeam().getId()) instanceof CustomTeam;
    }

    @Override
    public Match playFriendlyMatch(Long matchId, String command) {
        Match match = get(matchId);
        return this.update(PlayMatchManager.playFriendlyMatch(match, command, isUserTeamHome(match)));
    }

    @Override
    public void deleteAll() {
        dataStore.deleteAll();
    }

}
