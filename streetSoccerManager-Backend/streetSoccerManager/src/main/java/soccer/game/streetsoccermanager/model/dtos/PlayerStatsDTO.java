package soccer.game.streetsoccermanager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerStatsDTO {
    private Long id;
    private int skills;
    private int physical;
    private int overallRating;
}
