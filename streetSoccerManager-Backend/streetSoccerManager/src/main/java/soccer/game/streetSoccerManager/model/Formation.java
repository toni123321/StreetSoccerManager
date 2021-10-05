package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Formation {

    private int id;
    private String name; // 1-2-1

    public Formation(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
