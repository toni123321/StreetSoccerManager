package soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.game.streetSoccerManager.model.User;

public interface IUserJPARepository extends JpaRepository<User, Long> {

}
