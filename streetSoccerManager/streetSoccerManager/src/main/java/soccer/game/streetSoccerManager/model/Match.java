package soccer.game.streetSoccerManager.model;

import java.util.ArrayList;
import java.util.List;

public class Match {
    protected int Id;

    protected Team homeTeam;
    protected Team awayTeam;

    // result
    protected int homeTeamGoals;
    protected int awayTeamGoals;

    protected Kit homeTeamKit;
    protected Kit awayTeamKit;

    protected Stadium stadium;

    // Goals
    protected String matchStatistic;

    protected List<Player> homeTeamPlayers;
    protected List<Player> awayTeamPlayers;


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
