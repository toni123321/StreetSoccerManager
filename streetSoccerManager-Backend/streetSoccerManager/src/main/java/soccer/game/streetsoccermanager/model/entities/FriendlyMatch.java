package soccer.game.streetsoccermanager.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@Entity
@NoArgsConstructor
@Data
public class FriendlyMatch extends Match {

    public FriendlyMatch(Team homeTeam, Team awayTeam, String result, String statistic, int currentMinute) {
        super(homeTeam, awayTeam, result, statistic, currentMinute);
    }

    public FriendlyMatch(Long id, Team homeTeam, Team awayTeam, String result, String statistic, int currentMinute) {
        super(id, homeTeam, awayTeam, result, statistic, currentMinute);
    }
}
