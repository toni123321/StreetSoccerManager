package soccer.game.streetSoccerManager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name ="position")
@NoArgsConstructor
@Data
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


}
