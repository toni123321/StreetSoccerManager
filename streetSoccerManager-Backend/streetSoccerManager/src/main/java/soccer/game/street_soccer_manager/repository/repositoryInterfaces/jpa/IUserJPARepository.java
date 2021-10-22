package soccer.game.street_soccer_manager.repository.repositoryInterfaces.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.game.street_soccer_manager.model.User;

public interface IUserJPARepository extends JpaRepository<User, Long> {

}
