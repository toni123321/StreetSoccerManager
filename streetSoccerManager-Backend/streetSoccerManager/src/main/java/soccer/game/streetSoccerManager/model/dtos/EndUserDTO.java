package soccer.game.streetSoccerManager.model.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class EndUserDTO {
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private double points;

    public EndUserDTO(Long id, String email, String password, String nickname, double points) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.points = points;
    }
}
