package soccer.game.streetsoccermanager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StartFriendlyMatchDTO {
    private TeamDTO homeTeam;
    private TeamDTO awayTeam;
}
