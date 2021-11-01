package soccer.game.streetSoccerManager.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormationDTO {
    private Long id;
    private String name;

    public FormationDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
