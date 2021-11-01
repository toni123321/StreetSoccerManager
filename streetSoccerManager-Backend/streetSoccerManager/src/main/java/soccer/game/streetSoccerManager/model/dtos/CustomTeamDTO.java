package soccer.game.streetSoccerManager.model.dtos;

import lombok.Getter;
import lombok.Setter;
import soccer.game.streetSoccerManager.model.entities.Formation;
import soccer.game.streetSoccerManager.model.entities.User;

@Getter
@Setter
public class CustomTeamDTO {
    private Long id;
    private String name;
    private Formation formation;
    private User manager;

    public CustomTeamDTO(Long id, String name, Formation formation, User manager) {
        this.id = id;
        this.name = name;
        this.formation = formation;
        this.manager = manager;
    }
}
