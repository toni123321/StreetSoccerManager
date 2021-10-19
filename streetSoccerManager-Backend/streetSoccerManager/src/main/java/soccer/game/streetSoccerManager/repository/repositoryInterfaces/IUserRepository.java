package soccer.game.streetSoccerManager.interfaces.repositoryInterfaces;

import soccer.game.streetSoccerManager.model.User;

import java.util.List;

public interface IUserRepository {
    List<User> getAll();
    User get(int id);
    Boolean delete(int id);
    Boolean add(User user);
    Boolean update(User user);
}
