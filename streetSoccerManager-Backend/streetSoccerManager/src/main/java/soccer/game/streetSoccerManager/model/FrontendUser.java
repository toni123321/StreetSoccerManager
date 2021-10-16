package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FrontendUser)) return false;
        if (!super.equals(o)) return false;
        FrontendUser that = (FrontendUser) o;
        return Double.compare(that.getPoints(), getPoints()) == 0 && Objects.equals(getNickname(), that.getNickname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNickname(), getPoints());
    }
}
