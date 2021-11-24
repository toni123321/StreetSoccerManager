package soccer.game.streetSoccerManager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import soccer.game.streetSoccerManager.model.entities.Team;

@Data
@NoArgsConstructor
public class FriendlyMatchDTO {
    private Long id;
    private Team homeTeam;
    private Team awayTeam;
    private String result;
    private String statistic;

}
