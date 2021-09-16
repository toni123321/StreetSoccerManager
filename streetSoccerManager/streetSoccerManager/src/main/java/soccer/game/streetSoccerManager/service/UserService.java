package soccer.game.streetSoccerManager.service;

import soccer.game.streetSoccerManager.model.Admin;
import soccer.game.streetSoccerManager.model.FrontendUser;
import soccer.game.streetSoccerManager.model.Team;
import soccer.game.streetSoccerManager.model.User;
import soccer.game.streetSoccerManager.repository.FakeDatabase;

import java.util.List;

public class UserService {

    private List<User> users;
    private FakeDatabase fakeDatabase = new FakeDatabase();

    public UserService() {
        this.users = fakeDatabase.getUsers();
    }


    public List<User> getAllUsers() {
        return users;
    }

    public User getUser(int id) {
        for (User user : users) {
            if (user.getId() == id)
                return user;
        }
        return null;
    }

    public boolean deleteUser(int id) {
        User user = getUser(id);
        if (user == null){
            return false;
        }

        return users.remove(user);
    }


    public boolean addUser(User user) {
        if (this.getUser(user.getId()) != null){
            return false;
        }
        users.add(user);
        return true;
    }

    public boolean updateUser(User user) {
        User oldUser = this.getUser(user.getId());
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
