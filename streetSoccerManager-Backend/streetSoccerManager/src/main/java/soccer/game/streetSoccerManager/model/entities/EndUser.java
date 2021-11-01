package soccer.game.streetSoccerManager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Objects;


@Entity
@NoArgsConstructor
@Data
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

}
