package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Hashtable;

@Getter
@Setter
public class TournamentMatch extends Match {

    private Tournament tournament;

    public TournamentMatch(Long id, Team homeTeam, Team awayTeam, Hashtable<Player, Position> homeTeamPositions, Hashtable<Player, Position> awayTeamPositions, int homeTeamGoals, int awayTeamGoals, String matchStatistic, Tournament tournament) {
        super(id, homeTeam, awayTeam, homeTeamPositions, awayTeamPositions, homeTeamGoals, awayTeamGoals, matchStatistic);
        this.tournament = tournament;
    }

}
