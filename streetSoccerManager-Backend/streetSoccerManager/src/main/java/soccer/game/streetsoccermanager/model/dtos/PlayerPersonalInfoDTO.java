package soccer.game.streetsoccermanager.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Data
@NoArgsConstructor
public class PlayerPersonalInfoDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Calendar dob;

}
