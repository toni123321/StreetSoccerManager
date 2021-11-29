package soccer.game.streetsoccermanager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import soccer.game.streetsoccermanager.model.entities.Team;

@Data
@NoArgsConstructor
public class StartFriendlyMatchDTO {
    private Team homeTeam;
    private Team awayTeam;
}
