//package soccer.game.streetSoccerManager.model.entities;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.*;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.OneToOne;
//import java.util.Objects;
//
//
//@Entity
//@NoArgsConstructor
//@Data
//@EqualsAndHashCode(callSuper = true, exclude = {"team"})
//public class EndUser extends UserEntity {
//    private String nickname;
//    private double points;
//    @OneToOne(mappedBy = "manager", cascade = CascadeType.ALL)
//    @JsonIgnore
//    protected CustomTeam team;
//
//    public EndUser(Long id, String email, String password, String nickname, double points) {
//        super(id, email, password);
//        this.nickname = nickname;
//        this.points = points;
//    }
//
//    public EndUser(String email, String password, String nickname, double points) {
//        super(email, password);
//        this.nickname = nickname;
//        this.points = points;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof EndUser)) return false;
//        if (!super.equals(o)) return false;
//        EndUser endUser = (EndUser) o;
//        return Double.compare(endUser.getPoints(), getPoints()) == 0 && Objects.equals(getNickname(), endUser.getNickname());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), getNickname(), getPoints());
//    }
//}
