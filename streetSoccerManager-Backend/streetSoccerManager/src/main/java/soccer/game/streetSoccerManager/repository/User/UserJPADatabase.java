package soccer.game.streetSoccerManager.repository.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.model.entities.UserEntity;
import soccer.game.streetSoccerManager.repository_interfaces.jpa.IUserJPARepository;
import soccer.game.streetSoccerManager.repository_interfaces.IUserRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserJPADatabase implements IUserRepository {
    @Autowired
    IUserJPARepository userRepo;

    @Override
    public List<UserEntity> getAll() {
        return userRepo.findAll();
    }

    @Override
    public UserEntity get(Long id) {
        UserEntity userEntity = userRepo.findById(id).orElse(null);
        return userEntity;
    }

    @Override
    public UserEntity getByEmail(String email) {
        UserEntity userEntity = userRepo.findUserByEmail(email).orElse(null);
        return userEntity;
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
    public UserEntity add(UserEntity userEntity) {
        Optional<UserEntity> existingUser = userRepo.findUserByEmail(userEntity.getEmail());
        if(!existingUser.isPresent()) {
            return userRepo.save(userEntity);
        }
        return null;
    }

    @Override
    public UserEntity update(UserEntity userEntity) {
        if(userEntity.getId() != null) {
            return userRepo.save(userEntity);
        }
        return null;
    }

    @Override
    public void deleteAll() {
        userRepo.deleteAll();
    }
}
