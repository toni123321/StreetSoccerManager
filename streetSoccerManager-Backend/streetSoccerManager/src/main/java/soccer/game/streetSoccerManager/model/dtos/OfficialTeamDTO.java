package soccer.game.streetSoccerManager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import soccer.game.streetSoccerManager.model.entities.Formation;

@Data
@NoArgsConstructor
public class OfficialTeamDTO extends TeamDTO{
    private String managerName;

    public OfficialTeamDTO(Long id, String name, Formation formation, int rating, String managerName) {
        super(id, name, formation, rating);
        this.managerName = managerName;
    }
}


