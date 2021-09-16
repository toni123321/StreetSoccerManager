package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.Setter;

public class Formation {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String name;

    public Formation(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
