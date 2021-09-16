package soccer.game.streetSoccerManager.model;


import lombok.Getter;
import lombok.Setter;

public class Team {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Formation formation;


    @Getter
    @Setter
    private User manager;


    public Team(int id, String name, Formation formation, User manager) {
        this.id = id;
        this.name = name;
        this.formation = formation;
        this.manager = manager;
    }
}
