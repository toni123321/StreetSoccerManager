package soccer.game.streetSoccerManager.model;

public class FriendlyMatch extends Match {

    public FriendlyMatch(int id, Team homeTeam, Team awayTeam, int homeTeamGoals, int awayTeamGoals, String matchStatistic) {
        super(id, homeTeam, awayTeam, homeTeamGoals, awayTeamGoals, matchStatistic);
    }
}
