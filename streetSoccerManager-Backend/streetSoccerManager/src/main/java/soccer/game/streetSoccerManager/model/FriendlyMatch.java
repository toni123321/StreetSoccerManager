package soccer.game.streetSoccerManager.model;

import java.util.Hashtable;

public class FriendlyMatch extends Match {

    public FriendlyMatch(Long id, Team homeTeam, Team awayTeam, Hashtable<Player, Position> homeTeamPositions, Hashtable<Player, Position> awayTeamPositions, int homeTeamGoals, int awayTeamGoals, String matchStatistic) {
        super(id, homeTeam, awayTeam, homeTeamPositions, awayTeamPositions, homeTeamGoals, awayTeamGoals, matchStatistic);
    }
}
