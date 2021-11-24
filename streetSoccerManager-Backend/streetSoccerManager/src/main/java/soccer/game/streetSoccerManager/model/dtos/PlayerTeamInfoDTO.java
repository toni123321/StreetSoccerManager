package soccer.game.streetSoccerManager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import soccer.game.streetSoccerManager.model.entities.Team;


@Data
@NoArgsConstructor
public class PlayerTeamInfoDTO {
    private Long id;
    private int kitNr;
    private Team team;

}
