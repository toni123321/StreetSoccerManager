package soccer.game.streetSoccerManager.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class MatchStatistic {
    private Long id;
    // result
    private int homeTeamGoals;
    private int awayTeamGoals;

    // Goals
    private String statistic;
}
