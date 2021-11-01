package soccer.game.streetSoccerManager.model.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;


@Entity
@Table(name ="player")
@NoArgsConstructor
@Data
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
}
