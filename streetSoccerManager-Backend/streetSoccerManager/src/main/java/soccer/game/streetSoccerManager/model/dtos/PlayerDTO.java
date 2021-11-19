package soccer.game.streetSoccerManager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import soccer.game.streetSoccerManager.model.entities.PlayerAdditionalInfo;
import soccer.game.streetSoccerManager.model.entities.PlayerPersonalInfo;
import soccer.game.streetSoccerManager.model.entities.PlayerPositionInfo;
import soccer.game.streetSoccerManager.model.entities.PlayerTeamInfo;


@Data
@NoArgsConstructor
public class PlayerDTO {
    private Long id;
    private PlayerPersonalInfo playerPersonalInfo;
    private PlayerPositionInfo playerPositionInfo;
    private PlayerTeamInfo playerTeamInfo;
    private PlayerAdditionalInfo playerAdditionalInfo;

}
