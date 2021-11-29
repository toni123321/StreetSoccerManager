package soccer.game.streetsocceranager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import soccer.game.streetsocceranager.model.entities.Formation;

@Data
@NoArgsConstructor
public class OfficialTeamDTO{
    private Long id;
    private String name;
    private Formation formation;
    private int rating;
    private String managerName;

}


