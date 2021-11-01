package soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.game.streetSoccerManager.model.entities.MatchStatistic;

public interface IMatchStatisticJPARepository extends JpaRepository<MatchStatistic, Long> {
}
