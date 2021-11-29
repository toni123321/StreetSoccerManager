package soccer.game.streetsocceranager.repository_interfaces.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.game.streetsocceranager.model.entities.Position;

public interface IPositionJPARepository extends JpaRepository<Position, Long> {
    Position findFirstByName(String name);
}
