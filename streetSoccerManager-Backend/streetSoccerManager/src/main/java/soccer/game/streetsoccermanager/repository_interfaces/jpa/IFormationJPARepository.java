package soccer.game.streetsoccermanager.repository_interfaces.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.game.streetsoccermanager.model.entities.Formation;

public interface IFormationJPARepository extends JpaRepository<Formation, Long> {
}
