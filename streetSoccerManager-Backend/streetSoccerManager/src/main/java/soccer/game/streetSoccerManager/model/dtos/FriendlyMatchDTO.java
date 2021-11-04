package soccer.game.streetSoccerManager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import soccer.game.streetSoccerManager.model.entities.MatchInfo;
import soccer.game.streetSoccerManager.model.entities.MatchStatistic;

@Data
@NoArgsConstructor
public class FriendlyMatchDTO {
    private Long id;
    private MatchInfo matchInfo;
    private MatchStatistic matchStatistic;

    public FriendlyMatchDTO(Long id, MatchInfo matchInfo, MatchStatistic matchStatistic) {
        this.id = id;
        this.matchInfo = matchInfo;
        this.matchStatistic = matchStatistic;
    }
}
