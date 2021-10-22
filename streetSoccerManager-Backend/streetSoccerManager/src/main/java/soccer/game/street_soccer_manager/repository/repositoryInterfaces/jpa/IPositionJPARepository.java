package soccer.game.street_soccer_manager.repository.repositoryInterfaces.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.game.street_soccer_manager.model.Position;

public interface IPositionJPARepository extends JpaRepository<Position, Long> {
    Position findFirstByPosition(String position);
}
