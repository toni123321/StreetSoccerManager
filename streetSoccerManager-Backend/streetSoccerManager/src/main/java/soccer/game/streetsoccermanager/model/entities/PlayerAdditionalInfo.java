package soccer.game.streetsoccermanager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name ="player_additional_info")
@NoArgsConstructor
@Getter
@Setter
public class PlayerAdditionalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double price;
    @OneToOne(cascade = CascadeType.REMOVE)
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

    public PlayerAdditionalInfo(double price, PlayerStats playerStats) {
        this.price = price;
        this.playerStats = playerStats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerAdditionalInfo)) return false;
        PlayerAdditionalInfo that = (PlayerAdditionalInfo) o;
        return Double.compare(that.getPrice(), getPrice()) == 0 && getPlayerStats().equals(that.getPlayerStats());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrice(), getPlayerStats());
    }
}
