package soccer.game.streetSoccerManager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FriendlyMatchDTO {
    private Long id;

    public FriendlyMatchDTO(Long id) {
        this.id = id;
    }
}
