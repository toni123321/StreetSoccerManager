package soccer.game.streetSoccerManager.model.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Objects;


@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"manager"})
public class CustomTeam extends Team{
    @OneToOne
    @JoinColumn(name = "userId")
    private User manager;

    public CustomTeam(Long id, String name, Formation formation, User manager) {
        super(id, name, formation);
        this.manager = manager;
    }

    public CustomTeam(String name, Formation formation, User manager) {
        super(name, formation);
        this.manager = manager;
    }

}
