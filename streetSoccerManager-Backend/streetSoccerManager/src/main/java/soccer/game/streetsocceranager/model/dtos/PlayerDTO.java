package soccer.game.streetsocceranager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import soccer.game.streetsocceranager.model.entities.PlayerAdditionalInfo;
import soccer.game.streetsocceranager.model.entities.PlayerPersonalInfo;
import soccer.game.streetsocceranager.model.entities.PlayerPositionInfo;
import soccer.game.streetsocceranager.model.entities.PlayerTeamInfo;


@Data
@NoArgsConstructor
public class PlayerDTO {
    private Long id;
    private PlayerPersonalInfo playerPersonalInfo;
    private PlayerPositionInfo playerPositionInfo;
    private PlayerTeamInfo playerTeamInfo;
    private PlayerAdditionalInfo playerAdditionalInfo;

}
