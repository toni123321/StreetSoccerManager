package soccer.game.streetSoccerManager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.Interfaces.IRepository;
import soccer.game.streetSoccerManager.Interfaces.IService;
import soccer.game.streetSoccerManager.model.Admin;
import soccer.game.streetSoccerManager.model.FrontendUser;
import soccer.game.streetSoccerManager.model.Team;
import soccer.game.streetSoccerManager.model.User;
import soccer.game.streetSoccerManager.repository.FakeDatabase;
import soccer.game.streetSoccerManager.repository.UserFakeDatabase;

import java.util.List;

@Service
public class UserService implements IService {

    private List<User> users;




    private IRepository fakeDatabase;

    public UserService(@Qualifier("userFakeDatabase") IRepository fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
        this.users = (List<User>) fakeDatabase.getAll();
    }


    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User get(Object id) {
        for (User user : users) {
            if (user.getId() == (int) id)
                return user;
        }
        return null;
    }

    @Override
    public Boolean delete(Object id) {
        User user = get(id);
        if (user == null){
            return false;
        }

        return users.remove(user);
    }


    @Override
    public Boolean add(Object user) {
        if (this.get(((User) user).getId()) != null){
            return false;
        }
        users.add(((User) user));
        return true;
    }

    @Override
    public Boolean update(Object user) {
        User oldUser = this.get(((User) user).getId());
        if (oldUser == null) {
            return false;
        }
        oldUser.setEmail(((User) user).getEmail());
        oldUser.setPassword(((User) user).getPassword());

        if(user instanceof FrontendUser){
            ((FrontendUser)oldUser).setNickname(((FrontendUser) user).getNickname());
            ((FrontendUser)oldUser).setPoints(((FrontendUser) user).getPoints());

        }
        else{
            ((Admin)oldUser).setFirstName(((Admin) user).getFirstName());
            ((Admin)oldUser).setLastName(((Admin) user).getLastName());
        }
        return true;
    }
}
