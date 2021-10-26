package soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.game.streetSoccerManager.model.entities.Position;

public interface IPositionJPARepository extends JpaRepository<Position, Long> {
    Position findFirstByPosition(String position);
}
