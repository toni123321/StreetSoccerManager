package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerStats {

    private Long id;
    private int overallRating;

    public PlayerStats(Long id, int overallRating) {
        this.id = id;
        this.overallRating = overallRating;
    }

}
