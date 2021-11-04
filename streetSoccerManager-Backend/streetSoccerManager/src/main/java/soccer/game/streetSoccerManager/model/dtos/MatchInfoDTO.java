package soccer.game.streetSoccerManager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import soccer.game.streetSoccerManager.model.entities.Team;

@Data
@NoArgsConstructor
public class MatchInfoDTO {
    private Long id;
    private Team homeTeam;
    private Team awayTeam;

    public MatchInfoDTO(Long id, Team homeTeam, Team awayTeam) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }
}
