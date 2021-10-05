package soccer.game.streetSoccerManager.model;


import lombok.Getter;
import lombok.Setter;

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
}
