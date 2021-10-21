package soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.game.streetSoccerManager.model.PlayerStats;

public interface IPlayerStatsJPARepository extends JpaRepository<PlayerStats, Long> {
}
