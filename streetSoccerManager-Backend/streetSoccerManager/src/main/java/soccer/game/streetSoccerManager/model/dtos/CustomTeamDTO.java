package soccer.game.streetSoccerManager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import soccer.game.streetSoccerManager.model.entities.Formation;
import soccer.game.streetSoccerManager.model.entities.UserEntity;

@Data
@NoArgsConstructor
public class CustomTeamDTO {
    private Long id;
    private String name;
    private Formation formation;
    private UserEntity manager;

    public CustomTeamDTO(Long id, String name, Formation formation, UserEntity manager) {
        this.id = id;
        this.name = name;
        this.formation = formation;
        this.manager = manager;
    }
}
