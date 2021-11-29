package soccer.game.streetsocceranager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import soccer.game.streetsocceranager.model.entities.UserEntity;
import soccer.game.streetsocceranager.repository_interfaces.IUserRepository;
import soccer.game.streetsocceranager.service_interfaces.IUserService;

import java.util.List;

@Service
public class UserService implements IUserService {

    private IUserRepository dataStore;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(@Qualifier("userJPADatabase") IUserRepository dataStore) {
        this.dataStore = dataStore;
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public List<UserEntity> getAll() {
        return dataStore.getAll();
    }

    @Override
    public UserEntity get(Long id) {
        return dataStore.get(id);
    }

    @Override
    public UserEntity getByEmail(String email){
        return dataStore.getByEmail(email);
    }

    @Override
    public Boolean delete(Long id) {
        return dataStore.delete(id);
    }


    @Override
    public UserEntity add(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity createdUser = dataStore.add(user);
        if(createdUser != null) {
            return createdUser;
        }
        return null;
    }

    @Override
    public UserEntity update(UserEntity user) {
        UserEntity updatedUser = dataStore.update(user);
        if(updatedUser != null) {
            return updatedUser;
        }
        return null;
    }

    @Override
    public void deleteAll() {
        dataStore.deleteAll();
    }
}
