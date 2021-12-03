package soccer.game.streetsoccermanager.model.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name ="team")
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
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
    @ToString.Exclude
    private Set<PlayerTeamInfo> playersTeamInfo;

    @OneToMany(mappedBy = "homeTeam", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    protected Set<Match> homeTeamsMatches;

    @OneToMany(mappedBy = "awayTeam", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    protected Set<Match> awayTeamMatches;

    public Team(Long id, String name, Formation formation) {
        this.id = id;
        this.name = name;
        this.formation = formation;
    }

    public Team(String name, Formation formation) {
        this.name = name;
        this.formation = formation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return getId().equals(team.getId()) && getName().equals(team.getName()) && getFormation().equals(team.getFormation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getFormation());
    }
}
