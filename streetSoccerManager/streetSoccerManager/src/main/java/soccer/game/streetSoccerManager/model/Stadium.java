package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.Setter;

public class Stadium {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private double price;


    public Stadium(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

}
