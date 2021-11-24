package soccer.game.streetSoccerManager.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@Entity
@NoArgsConstructor
@Data
public class FriendlyMatch extends Match {
    public FriendlyMatch(Team homeTeam, Team awayTeam, String result, String statistic) {
        super(homeTeam, awayTeam, result, statistic);
    }
}
