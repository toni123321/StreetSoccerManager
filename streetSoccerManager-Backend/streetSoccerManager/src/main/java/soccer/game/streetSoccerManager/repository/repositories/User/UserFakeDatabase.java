package soccer.game.streetSoccerManager.repository.repositories.User;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IUserRepository;
import soccer.game.streetSoccerManager.model.Admin;
import soccer.game.streetSoccerManager.model.EndUser;
import soccer.game.streetSoccerManager.model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserFakeDatabase implements IUserRepository {
    @Getter
    private List<User> users = new ArrayList<>();

    public UserFakeDatabase() {
        users.add(new EndUser(0l, "peter@gmail.com", "123", "pete", 100));
        users.add(new EndUser(1l, "john@gmail.com", "456", "jo", 10));

        users.add(new Admin(2l, "admin1@gmail.com", "admin1", "Admin1", "Admin1"));
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User get(Long id) {
        for (User user : users) {
            if (user.getId() == id)
                return user;
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        User user = get(id);
        if (user == null){
            return false;
        }

        return users.remove(user);
    }

    @Override
    public Boolean add(User user) {
        if (this.get(user.getId()) != null){
            return false;
        }
        users.add(user);
        return true;
    }

    @Override
    public Boolean update(User user) {

        User oldUser = this.get(user.getId());
        if (oldUser == null) {
            return false;
        }
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());

        if(user instanceof EndUser){
            ((EndUser)oldUser).setNickname(((EndUser) user).getNickname());
            ((EndUser)oldUser).setPoints(((EndUser) user).getPoints());

        }
        else{
            ((Admin)oldUser).setFirstName(((Admin) user).getFirstName());
            ((Admin)oldUser).setLastName(((Admin) user).getLastName());
        }
        return true;
    }
}
