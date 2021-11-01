package soccer.game.streetSoccerManager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name ="player_position_info")
@NoArgsConstructor
@Data
public class PlayerPositionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int positionIndex;
    @ManyToOne
    @JoinColumn(name="defaultPositionId")
    private Position defaultPosition;

    @ManyToOne
    @JoinColumn(name="currentPositionId", nullable = false)
    private Position currentPosition;
    private boolean isStarting;

    @OneToOne(mappedBy = "playerPositionInfo")
    @JsonIgnore
    protected Player player;

    public PlayerPositionInfo(Long id, int positionIndex, Position defaultPosition, Position currentPosition, boolean isStarting) {
        this.id = id;
        this.positionIndex = positionIndex;
        this.defaultPosition = defaultPosition;
        this.currentPosition = currentPosition;
        this.isStarting = isStarting;
    }
}
