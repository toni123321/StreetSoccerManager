package soccer.game.streetSoccerManager.model;

public class Admin extends User {
    private String firstName;
    private String lastName;

    public Admin(int id, String email, String password, String firstName, String lastName) {
        super(id, email, password);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
