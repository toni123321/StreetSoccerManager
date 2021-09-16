package soccer.game.streetSoccerManager.model;

public class TournamentMatch extends Match {
    private Tournament tournament;

    public TournamentMatch(int id, Team homeTeam, Team awayTeam, int homeTeamGoals, int awayTeamGoals, Kit homeTeamKit, Kit awayTeamKit, Stadium stadium, String matchStatistic, Tournament tournament) {
        super(id, homeTeam, awayTeam, homeTeamGoals, awayTeamGoals, homeTeamKit, awayTeamKit, stadium, matchStatistic);
        this.tournament = tournament;
    }
}
