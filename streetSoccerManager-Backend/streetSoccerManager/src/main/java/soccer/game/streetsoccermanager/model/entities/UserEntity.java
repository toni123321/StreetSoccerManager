package soccer.game.streetsoccermanager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name ="user")
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"team"})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    @Column(nullable = true)
    private String firstName;
    @Column(nullable = true)
    private String lastName;
    @Column(nullable = true)
    private String nickname;
    @Column(nullable = true)
    private int points;
    private String role;

    @OneToOne(mappedBy = "manager", cascade = CascadeType.ALL)
    @JsonIgnore
    protected CustomTeam team;

    public UserEntity(Long id, String email, String password, String firstName, String lastName, String nickname) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
    }

    public UserEntity(String email, String password, String firstName, String lastName, String nickname) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
    }

}
