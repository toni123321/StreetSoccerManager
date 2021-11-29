package soccer.game.streetsoccermanager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import soccer.game.streetsoccermanager.model.entities.PlayerAdditionalInfo;
import soccer.game.streetsoccermanager.model.entities.PlayerPersonalInfo;
import soccer.game.streetsoccermanager.model.entities.PlayerPositionInfo;
import soccer.game.streetsoccermanager.model.entities.PlayerTeamInfo;


@Data
@NoArgsConstructor
public class PlayerDTO {
    private Long id;
    private PlayerPersonalInfo playerPersonalInfo;
    private PlayerPositionInfo playerPositionInfo;
    private PlayerTeamInfo playerTeamInfo;
    private PlayerAdditionalInfo playerAdditionalInfo;

}
