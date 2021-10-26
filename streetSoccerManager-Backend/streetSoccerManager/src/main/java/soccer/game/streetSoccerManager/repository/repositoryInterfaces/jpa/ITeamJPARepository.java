package soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.game.streetSoccerManager.model.entities.Team;

public interface ITeamJPARepository extends JpaRepository<Team, Long> {
}
