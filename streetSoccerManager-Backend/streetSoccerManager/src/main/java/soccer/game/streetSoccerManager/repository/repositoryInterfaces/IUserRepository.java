package soccer.game.streetSoccerManager.repository.repositoryInterfaces;

import soccer.game.streetSoccerManager.model.User;

import java.util.List;

public interface IUserRepository {
    List<User> getAll();
    User get(Long id);
    Boolean delete(Long id);
    Boolean add(User user);
    Boolean update(User user);
}
