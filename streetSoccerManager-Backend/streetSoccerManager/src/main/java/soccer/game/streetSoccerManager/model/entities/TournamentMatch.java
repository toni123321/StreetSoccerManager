package soccer.game.streetSoccerManager.model.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class TournamentMatch extends Match {

    private Tournament tournament;

    public TournamentMatch(Long id, MatchInfo matchInfo, MatchStatistic matchStatistic, Tournament tournament) {
        super(id, matchInfo, matchStatistic);
        this.tournament = tournament;
    }
}
