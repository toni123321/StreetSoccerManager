package soccer.game.streetSoccerManager.model.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Map;


@Entity
@NoArgsConstructor
@Data
public class FriendlyMatch extends Match {
    public FriendlyMatch(Long id, Team homeTeam, Team awayTeam, String result, String statistic) {
        super(id, homeTeam, awayTeam, result, statistic);
    }
}
