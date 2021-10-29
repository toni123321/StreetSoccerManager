package soccer.game.streetSoccerManager.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor

public class MatchInfo {
    private Long id;
    private Team homeTeam;
    private Team awayTeam;
    private Map<Player, Position> homeTeamPositions;
    private Map<Player, Position> awayTeamPositions;


}
