package soccer.game.streetSoccerManager.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class CustomTeam extends Team{
    @OneToOne
    @JoinColumn(name = "userId")
    private User manager;

    public CustomTeam(Long id, String name, Formation formation, User manager) {
        super(id, name, formation);
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomTeam)) return false;
        if (!super.equals(o)) return false;
        CustomTeam that = (CustomTeam) o;
        return Objects.equals(manager, that.manager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), manager);
    }
}
