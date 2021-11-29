package soccer.game.streetsoccermanager.repository_interfaces.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.game.streetsoccermanager.model.entities.Position;

public interface IPositionJPARepository extends JpaRepository<Position, Long> {
    Position findFirstByName(String name);
}
