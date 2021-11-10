package soccer.game.streetSoccerManager.repository_interfaces.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.game.streetSoccerManager.model.entities.PlayerTeamInfo;

public interface IPlayerTeamInfoJPARepository extends JpaRepository<PlayerTeamInfo, Long> {
}
