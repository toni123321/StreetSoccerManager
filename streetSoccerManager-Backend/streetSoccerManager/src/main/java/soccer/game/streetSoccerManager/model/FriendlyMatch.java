package soccer.game.streetSoccerManager.model;

public class FriendlyMatch extends Match {

    public FriendlyMatch(int id, Team homeTeam, Team awayTeam, int homeTeamGoals, int awayTeamGoals, Kit homeTeamKit, Kit awayTeamKit, Stadium stadium, String matchStatistic) {
        super(id, homeTeam, awayTeam, homeTeamGoals, awayTeamGoals, matchStatistic);
    }
}
