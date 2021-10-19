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
    private String manager;
    private User user;


    public Team(int id, String name, Formation formation, String manager, User user) {
        this.id = id;
        //idCounter++;
        this.name = name;
        this.formation = formation;
        this.manager = manager;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return getId() == team.getId() && Objects.equals(getName(),
                team.getName()) && Objects.equals(getFormation(),
                team.getFormation()) && Objects.equals(getManager(),
                team.getManager()) && Objects.equals(getUser(), team.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(),
                getFormation(), getManager(), getUser());
    }
}
