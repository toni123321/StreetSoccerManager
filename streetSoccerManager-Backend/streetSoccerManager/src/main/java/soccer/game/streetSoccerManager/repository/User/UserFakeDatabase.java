package soccer.game.streetSoccerManager.repository.User;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.interfaces.repositoryInterfaces.IUserRepository;
import soccer.game.streetSoccerManager.model.Admin;
import soccer.game.streetSoccerManager.model.FrontendUser;
import soccer.game.streetSoccerManager.model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserFakeDatabase implements IUserRepository {

    @Getter
    private List<User> users = new ArrayList<>();

    public UserFakeDatabase() {
        users.add(new FrontendUser(1, "peter@gmail.com", "123", "pete", 100));
        users.add(new FrontendUser(2, "john@gmail.com", "456", "jo", 10));

        users.add(new Admin(3, "admin1@gmail.com", "admin1", "Admin1", "Admin1"));

    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public Boolean delete(int id) {
        return null;
    }

    @Override
    public Boolean add(User user) {
        return null;
    }

    @Override
    public Boolean update(User user) {
        return null;
    }
}
