package soccer.game.streetSoccerManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor

public class EndUser extends User {
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "points")
    private double points;
    @OneToOne(mappedBy = "manager")
    @JsonIgnore
    protected CustomTeam team;

    public EndUser(Long id, String email, String password, String nickname, double points) {
        super(id, email, password);
        this.nickname = nickname;
        this.points = points;
    }
    public EndUser(String email, String password, String nickname, double points) {
        super(email, password);
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
