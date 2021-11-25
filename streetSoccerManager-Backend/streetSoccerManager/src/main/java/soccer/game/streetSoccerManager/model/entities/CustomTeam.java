package soccer.game.streetSoccerManager.model.entities;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"manager"})
public class CustomTeam extends Team{
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "userId")
    private UserEntity manager;

    public CustomTeam(Long id, String name, Formation formation, UserEntity manager) {
        super(id, name, formation);
        this.manager = manager;
    }

    public CustomTeam(String name, Formation formation, UserEntity manager) {
        super(name, formation);
        this.manager = manager;
    }

}
