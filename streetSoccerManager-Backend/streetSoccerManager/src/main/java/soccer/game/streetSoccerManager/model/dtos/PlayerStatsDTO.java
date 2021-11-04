package soccer.game.streetSoccerManager.model.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class PlayerStatsDTO {
    private Long id;
    private int overallRating;

    public PlayerStatsDTO(Long id, int overallRating) {
        this.id = id;
        this.overallRating = overallRating;
    }
}
