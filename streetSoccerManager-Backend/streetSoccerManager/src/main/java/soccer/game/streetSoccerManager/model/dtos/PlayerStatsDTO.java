package soccer.game.streetSoccerManager.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerStatsDTO {
    private Long id;
    private int overallRating;

    public PlayerStatsDTO(Long id, int overallRating) {
        this.id = id;
        this.overallRating = overallRating;
    }
}
