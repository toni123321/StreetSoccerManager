package soccer.game.streetSoccerManager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Calendar;


@Entity
@Table(name ="player_personal_info")
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"id", "player"})
public class PlayerPersonalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    @Temporal(TemporalType.DATE)
    private Calendar dob;

    @OneToOne(mappedBy = "playerPersonalInfo", cascade = CascadeType.ALL)
    @JsonIgnore
    protected Player player;

    public PlayerPersonalInfo(Long id, String firstName, String lastName, Calendar dob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    public PlayerPersonalInfo(String firstName, String lastName, Calendar dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }
}
