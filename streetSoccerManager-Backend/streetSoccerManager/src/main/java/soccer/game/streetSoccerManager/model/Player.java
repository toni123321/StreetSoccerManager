package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Player {

    private static int idCounter = 0;
    private int id;
    private String firstName;
    private String lastName;
    private Date dob;
    private double price;
    private String defaultPosition;
    private String currentPosition;
    private int kitNr;
    private boolean isStarting;
    private PlayerStats playerStats;
    private Team team; // connection with team

    public Player(int id, String firstName, String lastName, Date dob, double price,
                  String defaultPosition, String currentPosition, int kitNr,
                  boolean isStarting, PlayerStats playerStats,  Team team) {
        this.id = id;
        //idCounter++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.price = price;
        this.defaultPosition = defaultPosition;
        this.currentPosition = currentPosition;
        this.kitNr = kitNr;
        this.isStarting = isStarting;
        this.playerStats = playerStats;
        this.team = team;
    }
}
