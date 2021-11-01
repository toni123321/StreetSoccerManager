package soccer.game.streetSoccerManager.model.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Map;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Data
public class FriendlyMatch extends Match {
    public FriendlyMatch(Long id, MatchInfo matchInfo, MatchStatistic matchStatistic) {
        super(id, matchInfo, matchStatistic);
    }
}
