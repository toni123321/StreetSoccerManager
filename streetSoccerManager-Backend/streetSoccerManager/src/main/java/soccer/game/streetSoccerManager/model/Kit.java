package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Kit {
    private int id;
    private String name;
    private String brand;
    private String model;
    private double price;
    private Team team;
    private boolean isFirst;

    public Kit(int id, String name, String brand, String model, double price, Team team, boolean isFirst) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.team = team;
        this.isFirst = isFirst;
    }
}
