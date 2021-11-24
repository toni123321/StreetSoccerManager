package soccer.game.streetSoccerManager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import soccer.game.streetSoccerManager.model.entities.Position;

@Data
@NoArgsConstructor
public class PlayerPositionInfoDTO {
    private Long id;
    private Position defaultPosition;
    private Position currentPosition;
    private boolean isStarting;

}
