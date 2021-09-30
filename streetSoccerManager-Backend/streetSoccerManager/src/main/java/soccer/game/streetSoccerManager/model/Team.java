package soccer.game.streetSoccerManager.model;


import lombok.Getter;
import lombok.Setter;

public class Team {


    private static int idCounter = 0;
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


    public Team(String name, Formation formation, User manager) {
        this.id = idCounter;
        idCounter++;
        this.name = name;
        this.formation = formation;
        this.manager = manager;
    }
}
