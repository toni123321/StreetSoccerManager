package soccer.game.streetSoccerManager.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name ="player")
@NoArgsConstructor
public class Player implements Comparable<Player>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name="playerPersonalInfoId")

    private PlayerPersonalInfo playerPersonalInfo;

    @OneToOne
    @JoinColumn(name="playerPositionInfoId")
    private PlayerPositionInfo playerPositionInfo;

    @OneToOne
    @JoinColumn(name="playerTeamInfoId")
    private PlayerTeamInfo playerTeamInfo;

    @OneToOne
    @JoinColumn(name="playerAdditionalInfoId")
    private PlayerAdditionalInfo playerAdditionalInfo;

    public Player(Long id, PlayerPersonalInfo playerPersonalInfo, PlayerPositionInfo playerPositionInfo, PlayerTeamInfo playerTeamInfo, PlayerAdditionalInfo  playerAdditionalInfo) {
        this.id = id;
        this.playerPersonalInfo = playerPersonalInfo;
        this.playerPositionInfo = playerPositionInfo;
        this.playerTeamInfo = playerTeamInfo;
        this.playerAdditionalInfo = playerAdditionalInfo;
    }

    // todo: Add implementation
    @Override
    public int compareTo(Player o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return Objects.equals(getId(), player.getId()) && Objects.equals(getPlayerPersonalInfo(), player.getPlayerPersonalInfo()) && Objects.equals(getPlayerPositionInfo(), player.getPlayerPositionInfo()) && Objects.equals(getPlayerTeamInfo(), player.getPlayerTeamInfo()) && Objects.equals(getPlayerAdditionalInfo(), player.getPlayerAdditionalInfo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPlayerPersonalInfo(), getPlayerPositionInfo(), getPlayerTeamInfo(), getPlayerAdditionalInfo());
    }
}
