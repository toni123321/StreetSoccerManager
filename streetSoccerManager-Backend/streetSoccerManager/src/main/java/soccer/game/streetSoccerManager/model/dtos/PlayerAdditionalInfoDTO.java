package soccer.game.streetSoccerManager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import soccer.game.streetSoccerManager.model.entities.PlayerStats;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
public class PlayerAdditionalInfoDTO {
    private Long id;
    private double price;
    private PlayerStats playerStats;

    public PlayerAdditionalInfoDTO(Long id, double price, PlayerStats playerStats) {
        this.id = id;
        this.price = price;
        this.playerStats = playerStats;
    }
}
