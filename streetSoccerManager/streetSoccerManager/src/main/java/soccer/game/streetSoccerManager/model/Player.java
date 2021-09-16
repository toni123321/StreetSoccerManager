package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Player {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    private String lastName;
    @Getter
    @Setter
    private Date dob;

    @Getter
    @Setter
    private double price;

    @Getter
    @Setter
    private Team team; // connection with team

    public Player(int id, String firstName, String lastName, Date dob, double price, Team team) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.price = price;
        this.team = team;
    }
}
