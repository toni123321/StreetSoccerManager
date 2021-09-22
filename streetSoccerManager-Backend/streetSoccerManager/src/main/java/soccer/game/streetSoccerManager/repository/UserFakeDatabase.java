package soccer.game.streetSoccerManager.repository;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.Interfaces.IRepository;
import soccer.game.streetSoccerManager.model.Admin;
import soccer.game.streetSoccerManager.model.FrontendUser;
import soccer.game.streetSoccerManager.model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserFakeDatabase implements IRepository {

    @Getter
    private List<User> users = new ArrayList<>();

    public UserFakeDatabase() {
        users.add(new FrontendUser(1, "peter@gmail.com", "123", "pete", 100));
        users.add(new FrontendUser(2, "john@gmail.com", "456", "jo", 10));

        users.add(new Admin(3, "admin1@gmail.com", "admin1", "Admin1", "Admin1"));

    }

    @Override
    public Object getAll() {
        return users;
    }

    @Override
    public Object get(Object obj) {
        return null;
    }

    @Override
    public Object delete(Object obj) {
        return null;
    }

    @Override
    public Object add(Object obj) {
        return null;
    }

    @Override
    public Object update(Object obj) {
        return null;
    }
}
