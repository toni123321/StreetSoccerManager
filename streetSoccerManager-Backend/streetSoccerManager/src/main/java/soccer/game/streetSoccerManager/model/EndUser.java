package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class EndUser extends User {
    private String nickname;
    private double points;

    public EndUser(Long id, String email, String password, String nickname, double points) {
        super(id, email, password);
        this.nickname = nickname;
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EndUser)) return false;
        if (!super.equals(o)) return false;
        EndUser that = (EndUser) o;
        return Double.compare(that.getPoints(), getPoints()) == 0 && Objects.equals(getNickname(), that.getNickname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNickname(), getPoints());
    }
}
