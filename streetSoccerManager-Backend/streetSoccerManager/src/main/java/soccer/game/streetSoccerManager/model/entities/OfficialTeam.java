package soccer.game.streetSoccerManager.model.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.Entity;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Data
public class OfficialTeam extends Team{

    private String managerName; // First and Last name of managers

    public OfficialTeam(Long id, String name, Formation formation, String managerName) {
        super(id, name, formation);
        this.managerName = managerName;
    }
}
