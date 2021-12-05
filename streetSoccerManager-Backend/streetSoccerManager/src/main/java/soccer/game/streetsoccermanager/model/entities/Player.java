package soccer.game.streetsoccermanager.model.entities;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name ="player")
@NoArgsConstructor
@Data
@EqualsAndHashCode()
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

}
