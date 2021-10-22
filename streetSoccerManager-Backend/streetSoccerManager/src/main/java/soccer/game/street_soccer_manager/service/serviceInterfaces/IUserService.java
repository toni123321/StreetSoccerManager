package soccer.game.street_soccer_manager.service.serviceInterfaces;

import soccer.game.street_soccer_manager.model.User;

import java.util.List;

public interface IUserService {
    List<User> getAll();
    User get(Long id);
    Boolean delete(Long id);
    Boolean add(User user);
    Boolean update(User user);
}
