package soccer.game.street_soccer_manager.model;


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
@Table(name ="team")
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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
    private Set<Player> players;

    public Team(Long id, String name, Formation formation) {
        this.id = id;
        this.name = name;
        this.formation = formation;
    }

    public Team(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return getId() == team.getId() && Objects.equals(getName(), team.getName()) && Objects.equals(getFormation(), team.getFormation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getFormation());
    }
}
