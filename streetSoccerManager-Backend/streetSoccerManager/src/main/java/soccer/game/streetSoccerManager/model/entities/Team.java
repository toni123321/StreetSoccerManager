package soccer.game.streetSoccerManager.model.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
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
    @JoinColumn(name="formationId", nullable=false)
    private Formation formation;

    @OneToMany(mappedBy="team")
    @JsonIgnore
    private Set<PlayerTeamInfo> playersTeamInfo;

    @OneToOne(mappedBy = "homeTeam")
    @JsonIgnore
    protected MatchInfo matchInfoHomeTeam;

    @OneToOne(mappedBy = "awayTeam")
    @JsonIgnore
    protected MatchInfo matchInfoAwayTeam;


    public Team(Long id, String name, Formation formation) {
        this.id = id;
        this.name = name;
        this.formation = formation;
    }
}
