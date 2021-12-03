package soccer.game.streetsoccermanager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MatchDTO {
    private Long id;
    private TeamDTO homeTeam;
    private TeamDTO awayTeam;
    private String result;
    private String statistic;
}
