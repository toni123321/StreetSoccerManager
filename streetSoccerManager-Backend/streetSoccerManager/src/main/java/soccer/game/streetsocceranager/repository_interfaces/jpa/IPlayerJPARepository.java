package soccer.game.streetsocceranager.repository_interfaces.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.game.streetsocceranager.model.entities.Player;

public interface IPlayerJPARepository extends JpaRepository<Player, Long> {
}
