package soccer.game.streetSoccerManager.repository_interfaces.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.game.streetSoccerManager.model.entities.PlayerPersonalInfo;

public interface IPlayerPersonalInfoJPARepository extends JpaRepository<PlayerPersonalInfo, Long> {
}
