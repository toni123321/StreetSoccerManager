package soccer.game.streetSoccerManager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name ="position")
@NoArgsConstructor
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String category;
    private String position;

    @OneToMany(mappedBy = "defaultPosition")
    @JsonIgnore
    protected Set<PlayerPositionInfo> playersDefaultPositions;

    @OneToMany(mappedBy="currentPosition")
    @JsonIgnore
    private Set<PlayerPositionInfo> playersCurrentPosition;

    public Position(Long id, String category, String position) {
        this.id = id;
        this.category = category;
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position1 = (Position) o;
        return Objects.equals(getId(), position1.getId()) && Objects.equals(getCategory(), position1.getCategory()) && Objects.equals(getPosition(), position1.getPosition()) && Objects.equals(getPlayersDefaultPositions(), position1.getPlayersDefaultPositions()) && Objects.equals(getPlayersCurrentPosition(), position1.getPlayersCurrentPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCategory(), getPosition(), getPlayersDefaultPositions(), getPlayersCurrentPosition());
    }
}
