package soccer.game.streetSoccerManager.model.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class AdminDTO {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public AdminDTO(Long id, String email, String password, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
