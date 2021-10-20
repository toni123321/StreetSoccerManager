package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Admin extends User {
    private String firstName;
    private String lastName;

    public Admin(Long id, String email, String password, String firstName, String lastName) {
        super(id, email, password);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin)) return false;
        if (!super.equals(o)) return false;
        Admin admin = (Admin) o;
        return Objects.equals(getFirstName(), admin.getFirstName()) && Objects.equals(getLastName(), admin.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getFirstName(), getLastName());
    }
}
