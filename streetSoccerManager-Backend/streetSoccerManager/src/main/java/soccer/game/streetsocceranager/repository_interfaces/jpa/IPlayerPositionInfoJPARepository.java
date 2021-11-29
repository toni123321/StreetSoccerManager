package soccer.game.streetsocceranager.repository_interfaces.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.game.streetsocceranager.model.entities.PlayerPositionInfo;

public interface IPlayerPositionInfoJPARepository extends JpaRepository<PlayerPositionInfo, Long> {
}
