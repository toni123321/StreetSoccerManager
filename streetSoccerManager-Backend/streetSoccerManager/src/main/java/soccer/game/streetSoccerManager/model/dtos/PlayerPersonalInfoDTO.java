package soccer.game.streetSoccerManager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;

@Data
@NoArgsConstructor
public class PlayerPersonalInfoDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Calendar dob;

    public PlayerPersonalInfoDTO(Long id, String firstName, String lastName, Calendar dob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }
}
