package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class CustomTeam extends Team{
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
