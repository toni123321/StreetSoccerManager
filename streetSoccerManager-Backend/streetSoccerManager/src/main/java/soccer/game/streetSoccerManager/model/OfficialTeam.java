package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class OfficialTeam extends Team{

    private String managerName; // First and Last name of managers

    public OfficialTeam(Long id, String name, Formation formation, String managerName) {
        super(id, name, formation);
        this.managerName = managerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OfficialTeam)) return false;
        if (!super.equals(o)) return false;
        OfficialTeam that = (OfficialTeam) o;
        return Objects.equals(managerName, that.managerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), managerName);
    }
}
