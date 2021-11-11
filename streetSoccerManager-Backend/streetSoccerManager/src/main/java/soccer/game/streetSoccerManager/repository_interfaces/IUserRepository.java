package soccer.game.streetSoccerManager.repository_interfaces;

import soccer.game.streetSoccerManager.model.entities.User;

import java.util.List;

public interface IUserRepository {
    List<User> getAll();
    User get(Long id);
    Boolean delete(Long id);
    User add(User user);
    User update(User user);
    void deleteAll();
}
