package soccer.game.streetSoccerManager.model;



public class Team {
    private Long id;
    private String name;
    private User manager;


    public Team(Long id, String name, User manager) {
        this.id = id;
        this.name = name;
        this.manager = manager; // connection with user
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }
}
