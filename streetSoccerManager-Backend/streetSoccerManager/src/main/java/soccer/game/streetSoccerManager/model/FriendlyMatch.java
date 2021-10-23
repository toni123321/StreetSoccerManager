package soccer.game.streetSoccerManager.model;

import java.util.HashMap;
import java.util.Hashtable;

public class FriendlyMatch extends Match {

    public FriendlyMatch(Long id, Team homeTeam, Team awayTeam, HashMap<Player, Position> homeTeamPositions, HashMap<Player, Position> awayTeamPositions, int homeTeamGoals, int awayTeamGoals, String matchStatistic) {
        super(id, homeTeam, awayTeam, homeTeamPositions, awayTeamPositions, homeTeamGoals, awayTeamGoals, matchStatistic);
    }
}
