package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.Setter;

public class TournamentMatch extends Match {
    @Getter
    @Setter
    private Tournament tournament;

    public TournamentMatch(int id, Team homeTeam, Team awayTeam, int homeTeamGoals, int awayTeamGoals, Kit homeTeamKit, Kit awayTeamKit, Stadium stadium, String matchStatistic, Tournament tournament) {
        super(id, homeTeam, awayTeam, homeTeamGoals, awayTeamGoals, matchStatistic);
        this.tournament = tournament;
    }
}
