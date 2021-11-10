package soccer.game.streetSoccerManager.repository_interfaces.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.game.streetSoccerManager.model.entities.Player;

public interface IPlayerJPARepository extends JpaRepository<Player, Long> {
}
