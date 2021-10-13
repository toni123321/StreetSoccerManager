package soccer.game.streetSoccerManager.model;


import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Team {
    private static int idCounter = 0;
    private int id;
    private String name;
    private Formation formation;
    private User manager;

    public Team(int id, String name, Formation formation, User manager) {
        this.id = id;
        //idCounter++;
        this.name = name;
        this.formation = formation;
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return getId() == team.getId() && getName().equals(team.getName()) && getFormation().equals(team.getFormation()) && getManager().equals(team.getManager());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getFormation(), getManager());
    }
}
