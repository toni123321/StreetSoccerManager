package soccer.game.streetSoccerManager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import soccer.game.streetSoccerManager.model.entities.Formation;

@Data
@NoArgsConstructor
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


