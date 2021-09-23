package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.Setter;

public class Tournament {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Team winner;
    @Getter
    @Setter
    private int nrOfTeams;


}