package soccer.game.streetSoccerManager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import soccer.game.streetSoccerManager.model.entities.Formation;

@Data
@NoArgsConstructor
public class TeamDTO {
    private Long id;
    private String name;
    private Formation formation;
    private int rating;
    private String managerName;
    private UserDTO manager;

}
