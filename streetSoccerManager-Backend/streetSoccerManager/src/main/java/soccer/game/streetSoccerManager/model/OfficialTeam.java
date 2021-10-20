package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class OfficialTeam extends Team{
    private String manager; // First and Last name of managers

    public OfficialTeam(Long id, String name, Formation formation, String manager) {
        super(id, name, formation);
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OfficialTeam)) return false;
        if (!super.equals(o)) return false;
        OfficialTeam that = (OfficialTeam) o;
        return Objects.equals(manager, that.manager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), manager);
    }
}
