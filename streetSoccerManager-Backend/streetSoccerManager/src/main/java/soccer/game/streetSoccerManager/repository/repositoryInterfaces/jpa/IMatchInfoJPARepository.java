package soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.game.streetSoccerManager.model.entities.MatchInfo;

public interface IMatchInfoJPARepository extends JpaRepository<MatchInfo, Long> {

}
