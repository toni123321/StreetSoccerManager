package soccer.game.street_soccer_manager.repository.repositoryInterfaces;

import soccer.game.street_soccer_manager.model.User;

import java.util.List;

public interface IUserRepository {
    List<User> getAll();
    User get(Long id);
    Boolean delete(Long id);
    Boolean add(User user);
    Boolean update(User user);
}
