package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
public class Player implements Comparable<Player>{

    private static int idCounter = 0;
    private int id;
    private int positionIndex;
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

    public Player(int id, int positionIndex, String firstName, String lastName, Date dob, double price,
                  String defaultPosition, String currentPosition, int kitNr,
                  boolean isStarting, PlayerStats playerStats,  Team team) {
        this.id = id;
        this.positionIndex = positionIndex;
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

    @Override
    public int compareTo(Player o) {
        return Integer.compare(getPositionIndex(), o.getPositionIndex());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return getId() == player.getId() && getPositionIndex() == player.getPositionIndex() && Double.compare(player.getPrice(), getPrice()) == 0 && getKitNr() == player.getKitNr() && isStarting() == player.isStarting() && Objects.equals(getFirstName(), player.getFirstName()) && Objects.equals(getLastName(), player.getLastName()) && Objects.equals(getDob(), player.getDob()) && Objects.equals(getDefaultPosition(), player.getDefaultPosition()) && Objects.equals(getCurrentPosition(), player.getCurrentPosition()) && Objects.equals(getPlayerStats(), player.getPlayerStats()) && Objects.equals(getTeam(), player.getTeam());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPositionIndex(), getFirstName(), getLastName(), getDob(), getPrice(), getDefaultPosition(), getCurrentPosition(), getKitNr(), isStarting(), getPlayerStats(), getTeam());
    }
}
