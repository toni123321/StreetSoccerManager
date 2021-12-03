package soccer.game.streetsoccermanager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;


@Entity
@Table(name ="playerStats")
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"id", "playerAdditionalInfo"})
public class PlayerStats {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int skills;
    private int physical;

    @OneToOne(mappedBy = "playerStats", cascade = CascadeType.ALL)
    @JsonIgnore
    protected PlayerAdditionalInfo playerAdditionalInfo;

    public PlayerStats(Long id, int skills, int physical) {
        this.id = id;
        this.skills = skills;
        this.physical = physical;
    }

    public PlayerStats(int skills, int physical) {
        this.skills = skills;
        this.physical = physical;
    }
}
