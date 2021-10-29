package soccer.game.streetSoccerManager.model.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.*;


@Getter
@Setter
@Data
public class Match {

    protected Long id;
    protected MatchInfo matchInfo;
    protected MatchStatistic matchStatistic;

    public Match(Long id, MatchInfo matchInfo,
                 MatchStatistic matchStatistic) {
        this.id = id;
        this.matchInfo = matchInfo;
        this.matchStatistic = matchStatistic;
    }


}
