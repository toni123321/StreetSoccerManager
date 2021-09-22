package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Match {

    @Getter
    @Setter
    protected int Id;

    @Getter
    @Setter
    protected Team homeTeam;
    @Getter
    @Setter
    protected Team awayTeam;

    @Getter
    @Setter
    protected List<Player> homeTeamPlayers;
    @Getter
    @Setter
    protected List<Player> awayTeamPlayers;

    @Getter
    @Setter
    protected Kit homeTeamKit;
    @Getter
    @Setter
    protected Kit awayTeamKit;

    @Getter
    @Setter
    protected Stadium stadium;

    @Getter
    @Setter
    // result
    protected int homeTeamGoals;
    @Getter
    @Setter
    protected int awayTeamGoals;

    @Getter
    @Setter
    // Goals
    protected String matchStatistic;




    public Match(int id, Team homeTeam, Team awayTeam, int homeTeamGoals, int awayTeamGoals, Kit homeTeamKit, Kit awayTeamKit, Stadium stadium, String matchStatistic) {
        Id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.homeTeamKit = homeTeamKit;
        this.awayTeamKit = awayTeamKit;
        this.stadium = stadium;
        this.matchStatistic = matchStatistic;
        this.homeTeamPlayers = new ArrayList<>();
        this.awayTeamPlayers = new ArrayList<>();
    }
}
