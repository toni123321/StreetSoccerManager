package soccer.game.streetsoccermanager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name ="player_position_info")
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"player"})
public class PlayerPositionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="defaultPositionId")
    private Position defaultPosition;

    @ManyToOne
    @JoinColumn(name="currentPositionId")
    private Position currentPosition;
    private boolean isStarting;

    @OneToOne(mappedBy = "playerPositionInfo", cascade = CascadeType.ALL)
    @JsonIgnore
    protected Player player;

    public PlayerPositionInfo(Long id, Position defaultPosition, Position currentPosition, boolean isStarting) {
        this.id = id;
        this.defaultPosition = defaultPosition;
        this.currentPosition = currentPosition;
        this.isStarting = isStarting;
    }
    public PlayerPositionInfo(Position defaultPosition, Position currentPosition, boolean isStarting) {
        this.defaultPosition = defaultPosition;
        this.currentPosition = currentPosition;
        this.isStarting = isStarting;
    }

}
