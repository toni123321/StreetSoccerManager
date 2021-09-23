package soccer.game.streetSoccerManager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.interfaces.repositoryInterfaces.IUserRepository;
import soccer.game.streetSoccerManager.interfaces.serviceInterfaces.IUserService;
import soccer.game.streetSoccerManager.model.Admin;
import soccer.game.streetSoccerManager.model.FrontendUser;
import soccer.game.streetSoccerManager.model.User;

import java.util.List;

@Service
public class UserService implements IUserService {

    private List<User> users;

    private IUserRepository fakeDatabase;

    public UserService(@Qualifier("userFakeDatabase") IUserRepository fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
        this.users = fakeDatabase.getAll();
    }


    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User get(int id) {
        for (User user : users) {
            if (user.getId() == id)
                return user;
        }
        return null;
    }

    @Override
    public Boolean delete(int id) {
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
