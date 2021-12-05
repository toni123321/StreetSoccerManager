package soccer.game.streetsoccermanager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;


@Entity
@Table(name ="player_personal_info")
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"player"})
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerPersonalInfo)) return false;
        PlayerPersonalInfo that = (PlayerPersonalInfo) o;
        return getFirstName().equals(that.getFirstName()) && getLastName().equals(that.getLastName()) && getDob().equals(that.getDob());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getDob());
    }
}
