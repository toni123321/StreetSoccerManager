package soccer.game.streetSoccerManager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name ="formation")
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"teams"})
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name; // 1-2-1


    @OneToMany(mappedBy="formation", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Team> teams;


    public Formation(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Formation(String name) {
        this.name = name;
    }

}
