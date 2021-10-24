package soccer.game.streetSoccerManager.model;

import java.util.HashMap;
import java.util.Map;

public class FriendlyMatch extends Match {

    public FriendlyMatch(Long id, Team homeTeam, Team awayTeam, Map<Player, Position> homeTeamPositions, Map<Player, Position> awayTeamPositions, int homeTeamGoals, int awayTeamGoals, String matchStatistic) {
        super(id, homeTeam, awayTeam, homeTeamPositions, awayTeamPositions, homeTeamGoals, awayTeamGoals, matchStatistic);
    }
}
