package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Player {

    private int id;
    private String firstName;
    private String lastName;
    private Date dob;
    private double price;
    private PlayerStats playerStats;
    private Team team; // connection with team

    public Player(int id, String firstName, String lastName, Date dob, double price, PlayerStats playerStats,  Team team) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.price = price;
        this.playerStats = playerStats;
        this.team = team;
    }
}
