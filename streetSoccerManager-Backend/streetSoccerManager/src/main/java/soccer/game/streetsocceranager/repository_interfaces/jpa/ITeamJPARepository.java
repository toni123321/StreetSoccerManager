package soccer.game.streetsocceranager.repository_interfaces.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.game.streetsocceranager.model.entities.Team;

public interface ITeamJPARepository extends JpaRepository<Team, Long> {
}
