package soccer.game.streetSoccerManager.model;

import java.util.Date;

public class Player {
    private int id;
    private String firstName;
    private String lastName;
    private Date dob;
    private Team team; // connection with team

    public Player(int id, String firstName, String lastName, Date dob, Team team) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Team getTeamId() {
        return team;
    }

    public void setTeamId(Team teamId) {
        this.team = team;
    }
}
