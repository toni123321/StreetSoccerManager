package soccer.game.streetSoccerManager.model.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Admin extends User {
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;

    public Admin(Long id, String email, String password, String firstName, String lastName) {
        super(id, email, password);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
