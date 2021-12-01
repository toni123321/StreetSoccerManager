package soccer.game.streetsoccermanager.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name ="player")
@NoArgsConstructor
@Getter
@Setter
public class Player{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="playerPersonalInfoId")
    private PlayerPersonalInfo playerPersonalInfo;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="playerPositionInfoId")
    private PlayerPositionInfo playerPositionInfo;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="playerTeamInfoId")
    private PlayerTeamInfo playerTeamInfo;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="playerAdditionalInfoId")
    private PlayerAdditionalInfo playerAdditionalInfo;

    public Player(Long id, PlayerPersonalInfo playerPersonalInfo, PlayerPositionInfo playerPositionInfo, PlayerTeamInfo playerTeamInfo, PlayerAdditionalInfo  playerAdditionalInfo) {
        this.id = id;
        this.playerPersonalInfo = playerPersonalInfo;
        this.playerPositionInfo = playerPositionInfo;
        this.playerTeamInfo = playerTeamInfo;
        this.playerAdditionalInfo = playerAdditionalInfo;
    }

    public Player(PlayerPersonalInfo playerPersonalInfo, PlayerPositionInfo playerPositionInfo, PlayerTeamInfo playerTeamInfo, PlayerAdditionalInfo  playerAdditionalInfo) {
        this.playerPersonalInfo = playerPersonalInfo;
        this.playerPositionInfo = playerPositionInfo;
        this.playerTeamInfo = playerTeamInfo;
        this.playerAdditionalInfo = playerAdditionalInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return getPlayerPersonalInfo().equals(player.getPlayerPersonalInfo()) && getPlayerPositionInfo().equals(player.getPlayerPositionInfo()) && getPlayerTeamInfo().equals(player.getPlayerTeamInfo()) && getPlayerAdditionalInfo().equals(player.getPlayerAdditionalInfo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayerPersonalInfo(), getPlayerPositionInfo(), getPlayerTeamInfo(), getPlayerAdditionalInfo());
    }
}
