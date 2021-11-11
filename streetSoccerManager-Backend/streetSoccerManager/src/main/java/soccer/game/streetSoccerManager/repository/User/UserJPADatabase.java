package soccer.game.streetSoccerManager.repository.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.model.entities.User;
import soccer.game.streetSoccerManager.repository_interfaces.jpa.IUserJPARepository;
import soccer.game.streetSoccerManager.repository_interfaces.IUserRepository;

import java.util.List;

@Repository
public class UserJPADatabase implements IUserRepository {
    @Autowired
    IUserJPARepository userRepo;

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public User get(Long id) {
        User user = userRepo.findById(id).orElse(null);
        return user;
    }

    @Override
    public Boolean delete(Long id) {
        if(get(id) != null) {
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public User add(User user) {
        if(user.getId() == null) {
            return userRepo.save(user);
        }
        return null;
    }

    @Override
    public User update(User user) {
        if(user.getId() != null) {
            return userRepo.save(user);
        }
        return null;
    }

    @Override
    public void deleteAll() {
        userRepo.deleteAll();
    }
}
