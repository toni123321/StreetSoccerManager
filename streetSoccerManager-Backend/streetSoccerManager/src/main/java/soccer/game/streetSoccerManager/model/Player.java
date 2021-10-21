package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name ="player")
@NoArgsConstructor
public class Player implements Comparable<Player>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int positionIndex;
    private String firstName;
    private String lastName;
    @Temporal(TemporalType.DATE)
    private Calendar dob;
    private double price;

    @OneToOne
    @JoinColumn(name="defaultPositionId")
    private Position defaultPosition;

    @ManyToOne
    @JoinColumn(name="currentPositionId", nullable = false)
    private Position currentPosition;

    private int kitNr;
    private boolean isStarting;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "playerStatsId")
    private PlayerStats playerStats;

    @ManyToOne
    @JoinColumn(name="teamId", nullable=false)
    private Team team; // connection with team

    public Player(Long id, int positionIndex, String firstName, String lastName, Calendar dob, double price,
                  Position defaultPosition, Position currentPosition, int kitNr,
                  boolean isStarting, PlayerStats playerStats, Team team) {
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

    public Player(int positionIndex, String firstName, String lastName, Calendar dob, double price,
                  Position defaultPosition, Position currentPosition, int kitNr,
                  boolean isStarting, PlayerStats playerStats, Team team) {
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
