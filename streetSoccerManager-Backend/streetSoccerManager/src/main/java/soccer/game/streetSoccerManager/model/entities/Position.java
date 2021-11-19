package soccer.game.streetSoccerManager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name ="position")
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"id", "playersDefaultPositions", "playersCurrentPosition"})
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String category;
    private String name;

    @OneToMany(mappedBy = "defaultPosition", cascade = CascadeType.ALL)
    @JsonIgnore
    protected Set<PlayerPositionInfo> playersDefaultPositions;

    @OneToMany(mappedBy="currentPosition", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<PlayerPositionInfo> playersCurrentPosition;

    public Position(Long id, String category, String name) {
        this.id = id;
        this.category = category;
        this.name = name;
    }


}
