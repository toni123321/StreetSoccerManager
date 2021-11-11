package soccer.game.streetSoccerManager.model.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import soccer.game.streetSoccerManager.model.entities.Position;

@Data
@NoArgsConstructor
public class PlayerPositionInfoDTO {
    private Long id;
    private Position defaultPosition;
    private Position currentPosition;
    private boolean isStarting;

    public PlayerPositionInfoDTO(Long id, Position defaultPosition, Position currentPosition, boolean isStarting) {
        this.id = id;
        this.defaultPosition = defaultPosition;
        this.currentPosition = currentPosition;
        this.isStarting = isStarting;
    }
}
