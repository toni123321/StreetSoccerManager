package soccer.game.streetsocceranager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import soccer.game.streetsocceranager.model.entities.Team;

@Data
@NoArgsConstructor
public class MatchDTO {
    private Long id;
    private Team homeTeam;
    private Team awayTeam;
    private String result;
    private String statistic;
}
