package soccer.game.streetsoccermanager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayFriendlyMatchResponseDTO {
    private Long id;
    private String result;
    private String statistic;
    private int currentMinute;
    private String command;
    private Boolean isMatchEnd;
}
