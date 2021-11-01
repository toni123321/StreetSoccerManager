package soccer.game.streetSoccerManager.model.dtos;

import lombok.Getter;
import lombok.Setter;
import soccer.game.streetSoccerManager.model.entities.Formation;
import soccer.game.streetSoccerManager.model.entities.User;

@Getter
@Setter
public class OfficialTeamDTO {
    private Long id;
    private String name;
    private Formation formation;
    private String managerName;

    public OfficialTeamDTO(Long id, String name, Formation formation, String managerName) {
        this.id = id;
        this.name = name;
        this.formation = formation;
        this.managerName = managerName;
    }
}


