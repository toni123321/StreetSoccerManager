package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.Setter;

import java.util.*;


@Getter
@Setter
public class Match {

    protected Long id;
    protected Team homeTeam;
    protected Team awayTeam;

    protected Map<Player, Position> homeTeamPositions;
    protected Map<Player, Position> awayTeamPositions;

    // result
    protected int homeTeamGoals;
    protected int awayTeamGoals;

    // Goals
    protected String matchStatistic;


    public Match(Long id, Team homeTeam, Team awayTeam,
                 Map<Player, Position> homeTeamPositions,
                 Map<Player, Position> awayTeamPositions,
                 int homeTeamGoals, int awayTeamGoals, String matchStatistic) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamPositions = homeTeamPositions;
        this.awayTeamPositions = awayTeamPositions;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.matchStatistic = matchStatistic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Match)) return false;
        Match match = (Match) o;
        return getHomeTeamGoals() == match.getHomeTeamGoals() && getAwayTeamGoals() == match.getAwayTeamGoals() && Objects.equals(getId(), match.getId()) && Objects.equals(getHomeTeam(), match.getHomeTeam()) && Objects.equals(getAwayTeam(), match.getAwayTeam()) && Objects.equals(getHomeTeamPositions(), match.getHomeTeamPositions()) && Objects.equals(getAwayTeamPositions(), match.getAwayTeamPositions()) && Objects.equals(getMatchStatistic(), match.getMatchStatistic());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getHomeTeam(), getAwayTeam(), getHomeTeamPositions(), getAwayTeamPositions(), getHomeTeamGoals(), getAwayTeamGoals(), getMatchStatistic());
    }
}
