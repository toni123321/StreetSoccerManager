package soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.game.streetSoccerManager.model.entities.PlayerPositionInfo;

public interface IPlayerPositionInfoJPARepository extends JpaRepository<PlayerPositionInfo, Long> {
}
