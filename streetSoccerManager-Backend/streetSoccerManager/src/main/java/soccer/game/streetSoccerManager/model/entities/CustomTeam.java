package soccer.game.streetSoccerManager.model.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Objects;


@Entity
@NoArgsConstructor
@Data
public class CustomTeam extends Team{
    @OneToOne
    @JoinColumn(name = "userId")
    private User manager;

    public CustomTeam(Long id, String name, Formation formation, User manager) {
        super(id, name, formation);
        this.manager = manager;
    }

}
