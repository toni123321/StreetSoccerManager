package soccer.game.streetSoccerManager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name ="playerStats")
@NoArgsConstructor
@Data
public class PlayerStats {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int overallRating;

    @OneToOne(mappedBy = "playerStats")
    @JsonIgnore
    protected PlayerAdditionalInfo playerAdditionalInfo;

    public PlayerStats(Long id, int overallRating) {
        this.id = id;
        this.overallRating = overallRating;
    }
}
