package soccer.game.streetSoccerManager.model.dtos;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class PositionDTO {
    private Long id;
    private String category;
    private String position;

    public PositionDTO(Long id, String category, String position) {
        this.id = id;
        this.category = category;
        this.position = position;
    }
}
