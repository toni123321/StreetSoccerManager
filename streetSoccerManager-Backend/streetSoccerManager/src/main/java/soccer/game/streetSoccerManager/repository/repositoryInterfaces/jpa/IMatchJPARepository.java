package soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.game.streetSoccerManager.model.entities.Match;

public interface IMatchJPARepository extends JpaRepository<Match, Long> {
}
