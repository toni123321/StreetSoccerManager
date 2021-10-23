package soccer.game.streetSoccerManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name ="playerStats")
@NoArgsConstructor
public class PlayerStats {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int overallRating;

    @OneToOne(mappedBy = "playerStats")
    @JsonIgnore
    protected Player player;

    public PlayerStats(Long id, int overallRating) {
        this.id = id;
        this.overallRating = overallRating;
    }

    public PlayerStats(int overallRating) {
        this.overallRating = overallRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerStats)) return false;
        PlayerStats stats = (PlayerStats) o;
        return getOverallRating() == stats.getOverallRating() && Objects.equals(getId(), stats.getId()) && Objects.equals(getPlayer(), stats.getPlayer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOverallRating(), getPlayer());
    }
}
