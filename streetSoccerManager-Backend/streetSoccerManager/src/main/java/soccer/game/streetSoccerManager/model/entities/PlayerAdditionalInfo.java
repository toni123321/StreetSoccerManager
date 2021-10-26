package soccer.game.streetSoccerManager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name ="player_additional_info")
@NoArgsConstructor
public class PlayerAdditionalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double price;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "playerStatsId")
    private PlayerStats playerStats;

    @OneToOne(mappedBy = "playerAdditionalInfo")
    @JsonIgnore
    protected Player player;
}
