package soccer.game.streetSoccerManager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import soccer.game.streetSoccerManager.model.entities.Formation;
import soccer.game.streetSoccerManager.model.entities.UserEntity;

@Data
@NoArgsConstructor
public class CustomTeamDTO extends TeamDTO{
    private UserEntity manager;

    public CustomTeamDTO(Long id, String name, Formation formation, int rating, UserEntity manager) {
        super(id, name, formation, rating);
        this.manager = manager;
    }
}
