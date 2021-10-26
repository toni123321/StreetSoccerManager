package soccer.game.streetSoccerManager.service.serviceInterfaces;

import soccer.game.streetSoccerManager.model.entities.User;

import java.util.List;

public interface IUserService {
    List<User> getAll();
    User get(Long id);
    Boolean delete(Long id);
    Boolean add(User user);
    Boolean update(User user);
}
