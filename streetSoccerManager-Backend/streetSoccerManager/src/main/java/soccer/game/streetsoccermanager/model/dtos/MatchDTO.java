package soccer.game.streetsoccermanager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import soccer.game.streetsoccermanager.model.entities.Team;

@Data
@NoArgsConstructor
public class MatchDTO {
    private Long id;
    private TeamDTO homeTeam;
    private TeamDTO awayTeam;
    private String result;
    private String statistic;
}
