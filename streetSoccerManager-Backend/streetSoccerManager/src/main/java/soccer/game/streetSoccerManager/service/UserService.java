package soccer.game.streetSoccerManager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IUserRepository;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IUserService;
import soccer.game.streetSoccerManager.model.User;

import java.util.List;

@Service
public class UserService implements IUserService {

    private IUserRepository dataStore;

    public UserService(@Qualifier("userFakeDatabase") IUserRepository dataStore) {
        this.dataStore = dataStore;
    }


    @Override
    public List<User> getAll() {
        return dataStore.getAll();
    }

    @Override
    public User get(Long id) {
        return dataStore.get(id);
    }

    @Override
    public Boolean delete(Long id) {
        return dataStore.delete(id);
    }


    @Override
    public Boolean add(User user) {
        user.setId(Long.valueOf(dataStore.getAll().size()));
        return dataStore.add(user);
    }

    @Override
    public Boolean update(User user) {
        return dataStore.update(user);
    }
}
