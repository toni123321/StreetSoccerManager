package soccer.game.streetSoccerManager.model.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class PlayerStatsDTO {
    private Long id;
    private int skills;
    private int physical;

    public PlayerStatsDTO(Long id, int skills, int physical) {
        this.id = id;
        this.skills = skills;
        this.physical = physical;
    }
}
