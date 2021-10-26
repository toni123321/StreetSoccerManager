package soccer.game.streetSoccerManager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name ="player_team_info")
@NoArgsConstructor
public class PlayerTeamInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int kitNr;
    @ManyToOne
    @JoinColumn(name="teamId", nullable=false)
    private Team team; // connection with team

    @OneToOne(mappedBy = "playerTeamInfo")
    @JsonIgnore
    protected Player player;
}
