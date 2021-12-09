package soccer.game.streetsoccermanager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import soccer.game.streetsoccermanager.model.entities.UserEntity;
import soccer.game.streetsoccermanager.repository_interfaces.IUserRepository;
import soccer.game.streetsoccermanager.service_interfaces.IUserService;

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
        if(get(id) != null) {
            dataStore.delete(id);
            return true;
        }
        return false;
    }


    @Override
    public UserEntity add(UserEntity user) {
        if(user.getId() == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return dataStore.add(user);
        }
        return null;
    }

    @Override
    public UserEntity update(UserEntity user) {
        if(user.getId() != null) {
            return dataStore.update(user);
        }
        return null;
    }

    @Override
    public void deleteAll() {
        dataStore.deleteAll();
    }
}