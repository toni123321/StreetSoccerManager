package soccer.game.streetSoccerManager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;

@Getter
@Setter
@Entity
@Table(name ="player_personal_info")
@NoArgsConstructor
public class PlayerPersonalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    @Temporal(TemporalType.DATE)
    private Calendar dob;

    @OneToOne(mappedBy = "playerPersonalInfo")
    @JsonIgnore
    protected Player player;

    public PlayerPersonalInfo(Long id, String firstName, String lastName, Calendar dob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }
}
