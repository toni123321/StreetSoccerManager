package soccer.game.street_soccer_manager.repository.repositoryInterfaces.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.game.street_soccer_manager.model.Formation;

public interface IFormationJPARepository extends JpaRepository<Formation, Long> {
}
