package soccer.game.streetsoccermanager.repository_interfaces.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.game.streetsoccermanager.model.entities.PlayerAdditionalInfo;

public interface IPlayerAdditionalInfoJPARepository extends JpaRepository<PlayerAdditionalInfo, Long> {

}
