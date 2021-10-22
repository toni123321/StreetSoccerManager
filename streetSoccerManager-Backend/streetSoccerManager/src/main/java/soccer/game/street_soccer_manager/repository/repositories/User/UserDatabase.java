package soccer.game.street_soccer_manager.repository.repositories.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.street_soccer_manager.model.User;
import soccer.game.street_soccer_manager.repository.repositoryInterfaces.jpa.IUserJPARepository;
import soccer.game.street_soccer_manager.repository.repositoryInterfaces.IUserRepository;

import java.util.List;

@Repository
public class UserDatabase implements IUserRepository {
    @Autowired
    IUserJPARepository repo;

    @Override
    public List<User> getAll() {
        return repo.findAll();
    }

    @Override
    public User get(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public Boolean delete(Long id) {
        if(get(id) != null) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean add(User user) {
        if(user.getId() == null) {
            repo.save(user);
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(User user) {
        if(user.getId() != null) {
            repo.save(user);
            return true;
        }
        return false;
    }
}
