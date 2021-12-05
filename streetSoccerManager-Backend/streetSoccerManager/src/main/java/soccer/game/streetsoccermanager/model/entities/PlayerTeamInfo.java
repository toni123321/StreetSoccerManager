package soccer.game.streetsoccermanager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name ="player_team_info")
@NoArgsConstructor
@Getter
@Setter
public class PlayerTeamInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int kitNr;
    @ManyToOne
    @JoinColumn(name="teamId")
    private Team team; // connection with team

    @OneToOne(mappedBy = "playerTeamInfo", cascade = CascadeType.ALL)
    @JsonIgnore
    protected Player player;

    public PlayerTeamInfo(Long id, int kitNr, Team team) {
        this.id = id;
        this.kitNr = kitNr;
        this.team = team;
    }
    public PlayerTeamInfo(int kitNr, Team team) {
        this.kitNr = kitNr;
        this.team = team;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerTeamInfo)) return false;
        PlayerTeamInfo that = (PlayerTeamInfo) o;
        return getKitNr() == that.getKitNr() && getTeam().equals(that.getTeam());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKitNr(), getTeam());
    }
}
