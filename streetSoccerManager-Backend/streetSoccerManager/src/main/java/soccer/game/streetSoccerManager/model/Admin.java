package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.Setter;

public class Admin extends User {
    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    public Admin(int id, String email, String password, String firstName, String lastName) {
        super(id, email, password);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
