package soccer.game.streetSoccerManager.model;

public class Kit {
    private int id;
    private String name;
    private double price;
    private String brand;
    private String model;


    private Team team;

    public Kit(int id, String name, double price, String brand, String model, Team team) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.model = model;
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }


}
