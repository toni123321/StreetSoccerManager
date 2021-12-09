package soccer.game.streetsoccermanager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayFriendlyMatchDTO {
    FriendlyMatchDTO friendlyMatch;
    String command;
}
