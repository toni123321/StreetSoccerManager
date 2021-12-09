package soccer.game.streetsoccermanager.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetsoccermanager.model.entities.UserEntity;
import soccer.game.streetsoccermanager.repository_interfaces.jpa.IUserJPARepository;
import soccer.game.streetsoccermanager.repository_interfaces.IUserRepository;

import java.util.List;

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
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public UserEntity getByEmail(String email) {
        return userRepo.findUserByEmail(email).orElse(null);
    }

    @Override
    public Boolean delete(Long id) {
        userRepo.deleteById(id);
        return true;
    }

    @Override
    public UserEntity add(UserEntity userEntity) {
        return userRepo.save(userEntity);
    }

    @Override
    public UserEntity update(UserEntity userEntity) {
        return userRepo.save(userEntity);
    }

    @Override
    public void deleteAll() {
        userRepo.deleteAll();
    }
}
