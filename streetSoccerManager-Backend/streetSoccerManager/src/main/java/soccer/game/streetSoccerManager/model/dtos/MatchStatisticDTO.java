package soccer.game.streetSoccerManager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MatchStatisticDTO {
    private Long id;
    // result
    private int homeTeamGoals;
    private int awayTeamGoals;

    // Goals
    private String statistic;

    public MatchStatisticDTO(Long id, int homeTeamGoals, int awayTeamGoals, String statistic) {
        this.id = id;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.statistic = statistic;
    }
}
