package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.Setter;

public class FrontendUser extends User {
    @Getter
    @Setter
    private String nickname;

    @Getter
    @Setter
    private double points;

    public FrontendUser(int id, String email, String password, String nickname, double points) {
        super(id, email, password);
        this.nickname = nickname;
        this.points = points;
    }
}
