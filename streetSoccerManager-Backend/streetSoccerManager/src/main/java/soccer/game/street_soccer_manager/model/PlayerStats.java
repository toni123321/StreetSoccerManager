package soccer.game.street_soccer_manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
}
