package soccer.game.streetsocceranager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import soccer.game.streetsocceranager.model.entities.Position;

@Data
@NoArgsConstructor
public class PlayerPositionInfoDTO {
    private Long id;
    private Position defaultPosition;
    private Position currentPosition;
    private boolean isStarting;

}
