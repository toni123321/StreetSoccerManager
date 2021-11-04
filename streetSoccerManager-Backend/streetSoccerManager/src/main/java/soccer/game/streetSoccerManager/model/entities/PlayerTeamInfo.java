package soccer.game.streetSoccerManager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name ="player_team_info")
@NoArgsConstructor
@Data
public class PlayerTeamInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int kitNr;
    @ManyToOne
    @JoinColumn(name="teamId")
    private Team team; // connection with team

    @OneToOne(mappedBy = "playerTeamInfo")
    @JsonIgnore
    protected Player player;

    public PlayerTeamInfo(Long id, int kitNr, Team team) {
        this.id = id;
        this.kitNr = kitNr;
        this.team = team;
    }
}
