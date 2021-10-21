package soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.game.streetSoccerManager.model.Player;

public interface IPlayerJPARepository extends JpaRepository<Player, Long> {
}
