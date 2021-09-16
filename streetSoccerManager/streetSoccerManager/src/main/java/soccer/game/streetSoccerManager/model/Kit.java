package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.Setter;

public class Kit {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String brand;
    @Getter
    @Setter
    private String model;

    @Getter
    @Setter
    private double price;

    @Getter
    @Setter
    private Team team;

    public Kit(int id, String name, String brand, String model, double price, Team team) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.team = team;
    }
}
