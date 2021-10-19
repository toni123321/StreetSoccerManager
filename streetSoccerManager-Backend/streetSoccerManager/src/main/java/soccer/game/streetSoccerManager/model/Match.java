package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Match {

    protected int Id;
    protected Team homeTeam;
    protected Team awayTeam;
    protected List<Player> homeTeamPlayers;
    protected List<Player> awayTeamPlayers;


    // result
    protected int homeTeamGoals;
    @Getter
    @Setter
    protected int awayTeamGoals;

    @Getter
    @Setter
    // Goals
    protected String matchStatistic;




    public Match(int id, Team homeTeam, Team awayTeam, int homeTeamGoals, int awayTeamGoals, String matchStatistic) {
        Id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.matchStatistic = matchStatistic;
        this.homeTeamPlayers = new ArrayList<>();
        this.awayTeamPlayers = new ArrayList<>();
    }
}
