package soccer.game.streetSoccerManager.model;


import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Team {
    private Long id;
    private String name;
    private Formation formation;

    public Team(Long id, String name, Formation formation) {
        this.id = id;
        this.name = name;
        this.formation = formation;
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
