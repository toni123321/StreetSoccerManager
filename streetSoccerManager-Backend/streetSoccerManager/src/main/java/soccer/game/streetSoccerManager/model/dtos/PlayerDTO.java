package soccer.game.streetSoccerManager.model.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import soccer.game.streetSoccerManager.model.entities.PlayerAdditionalInfo;
import soccer.game.streetSoccerManager.model.entities.PlayerPersonalInfo;
import soccer.game.streetSoccerManager.model.entities.PlayerPositionInfo;
import soccer.game.streetSoccerManager.model.entities.PlayerTeamInfo;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@Setter
@Data
public class PlayerDTO {
    private Long id;
    private PlayerPersonalInfo playerPersonalInfo;
    private PlayerPositionInfo playerPositionInfo;
    private PlayerTeamInfo playerTeamInfo;
    private PlayerAdditionalInfo playerAdditionalInfo;

    public PlayerDTO(Long id, PlayerPersonalInfo playerPersonalInfo, PlayerPositionInfo playerPositionInfo, PlayerTeamInfo playerTeamInfo, PlayerAdditionalInfo playerAdditionalInfo) {
        this.id = id;
        this.playerPersonalInfo = playerPersonalInfo;
        this.playerPositionInfo = playerPositionInfo;
        this.playerTeamInfo = playerTeamInfo;
        this.playerAdditionalInfo = playerAdditionalInfo;
    }
}
