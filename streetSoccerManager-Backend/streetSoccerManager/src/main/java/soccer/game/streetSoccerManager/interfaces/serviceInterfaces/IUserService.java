package soccer.game.streetSoccerManager.interfaces.serviceInterfaces;

import soccer.game.streetSoccerManager.model.User;

import java.util.List;

public interface IUserService {
    List<User> getAll();
    User get(int id);
    Boolean delete(int id);
    Boolean add(User user);
    Boolean update(User user);
}
