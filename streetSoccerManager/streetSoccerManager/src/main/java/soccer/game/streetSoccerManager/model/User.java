package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.Setter;

public class User {
    @Getter
    @Setter
    protected int id;
    @Getter
    @Setter
    protected String email;
    @Getter
    @Setter
    protected String password;

    public User(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

}
