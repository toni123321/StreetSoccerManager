package soccer.game.streetsoccermanager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamDTO {
    private Long id;
    private String name;
    private FormationDTO formation;
    private String managerName;
    private UserDTO manager;
}
