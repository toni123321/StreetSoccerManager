package soccer.game.streetSoccerManager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class FormationDTO {
    private Long id;
    private String name;

    public FormationDTO(String name) {
        this.name = name;
    }
    public FormationDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
