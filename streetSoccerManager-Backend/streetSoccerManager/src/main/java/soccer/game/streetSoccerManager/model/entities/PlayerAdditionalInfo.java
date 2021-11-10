package soccer.game.streetSoccerManager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name ="player_additional_info")
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"id", "player"})
public class PlayerAdditionalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double price;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "playerStatsId")
    private PlayerStats playerStats;

    @OneToOne(mappedBy = "playerAdditionalInfo", cascade = CascadeType.ALL)
    @JsonIgnore
    protected Player player;

    public PlayerAdditionalInfo(Long id, double price, PlayerStats playerStats) {
        this.id = id;
        this.price = price;
        this.playerStats = playerStats;
    }
}
