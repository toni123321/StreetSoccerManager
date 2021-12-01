package soccer.game.streetsoccermanager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name ="player_position_info")
@NoArgsConstructor
@Getter
@Setter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerPositionInfo)) return false;
        PlayerPositionInfo that = (PlayerPositionInfo) o;
        return isStarting() == that.isStarting() && getDefaultPosition().equals(that.getDefaultPosition()) && getCurrentPosition().equals(that.getCurrentPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDefaultPosition(), getCurrentPosition(), isStarting());
    }
}
