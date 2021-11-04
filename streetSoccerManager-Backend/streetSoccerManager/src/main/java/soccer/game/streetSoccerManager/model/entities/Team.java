package soccer.game.streetSoccerManager.model.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name ="team")
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name="formationId")
    private Formation formation;

    @OneToMany(mappedBy="team")
    @JsonIgnore
    private Set<PlayerTeamInfo> playersTeamInfo;

    @OneToMany(mappedBy = "homeTeam")
    @JsonIgnore
    protected Set<MatchInfo> matchInfoHomeTeam;

    @OneToMany(mappedBy = "awayTeam")
    @JsonIgnore
    protected Set<MatchInfo> matchInfoAwayTeam;

    @Column(nullable = true)
    private int rating;

    public Team(Long id, String name, Formation formation) {
        this.id = id;
        this.name = name;
        this.formation = formation;
    }
}
