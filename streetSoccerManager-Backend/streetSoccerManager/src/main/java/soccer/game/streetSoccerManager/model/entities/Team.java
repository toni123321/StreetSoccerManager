package soccer.game.streetSoccerManager.model.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
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
@EqualsAndHashCode(exclude = {"playersTeamInfo", "homeTeamsMatches", "awayTeamMatches"})
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name="formationId")
    private Formation formation;

    @OneToMany(mappedBy="team", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<PlayerTeamInfo> playersTeamInfo;

    @OneToMany(mappedBy = "homeTeam", cascade = CascadeType.ALL)
    @JsonIgnore
    protected Set<Match> homeTeamsMatches;

    @OneToMany(mappedBy = "awayTeam", cascade = CascadeType.ALL)
    @JsonIgnore
    protected Set<Match> awayTeamMatches;

    @Column(nullable = true)
    private int rating;

    public Team(Long id, String name, Formation formation) {
        this.id = id;
        this.name = name;
        this.formation = formation;
    }

    public Team(String name, Formation formation) {
        this.name = name;
        this.formation = formation;
    }

}
