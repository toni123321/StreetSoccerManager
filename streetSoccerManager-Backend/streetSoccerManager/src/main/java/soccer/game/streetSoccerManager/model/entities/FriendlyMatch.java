package soccer.game.streetSoccerManager.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@Entity
@NoArgsConstructor
@Data
public class FriendlyMatch extends Match {
    public FriendlyMatch(Long id, Team homeTeam, Team awayTeam, String result, String statistic) {
        super(id, homeTeam, awayTeam, result, statistic);
    }
}
