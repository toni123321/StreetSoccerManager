package soccer.game.streetsoccermanager.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import soccer.game.streetsoccermanager.exceptions.EntryNotValidException;
import soccer.game.streetsoccermanager.model.entities.UserEntity;
import soccer.game.streetsoccermanager.repository_interfaces.IUserRepository;
import soccer.game.streetsoccermanager.service_interfaces.IUserService;
import soccer.game.streetsoccermanager.validation.UserProfileValidation;

import javax.persistence.EntityExistsException;
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
    public UserEntity add(UserEntity user) throws EntityExistsException, EntryNotValidException {
        if(user.getId() == null) {
            if(getByEmail(user.getEmail()) instanceof UserEntity){
                throw new EntityExistsException("This email is already in use!");
            }
            if(UserProfileValidation.isEmailValid(user.getEmail()) && UserProfileValidation.isPasswordValid(user.getPassword())) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                return dataStore.add(user);
            }
            else if(Boolean.FALSE.equals(UserProfileValidation.isEmailValid(user.getEmail()))){
                throw new EntryNotValidException("Email is not valid!");
            }
            else{
                throw new EntryNotValidException("Password is not valid!");
            }
        }
        else{
            throw new EntityExistsException("User with such id already exists!");
        }
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
