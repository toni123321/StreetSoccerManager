package soccer.game.streetsoccermanager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayFriendlyMatchInputDTO {
    private Long id;
    private String command;
}
