package soccer.game.streetSoccerManager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import soccer.game.streetSoccerManager.model.entities.PlayerStats;


@Data
@NoArgsConstructor
public class PlayerAdditionalInfoDTO {
    private Long id;
    private double price;
    private PlayerStats playerStats;

}
