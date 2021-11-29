package soccer.game.streetsocceranager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import soccer.game.streetsocceranager.model.entities.PlayerStats;


@Data
@NoArgsConstructor
public class PlayerAdditionalInfoDTO {
    private Long id;
    private double price;
    private PlayerStats playerStats;

}
