package soccer.game.streetsoccermanager.service_interfaces;

import javassist.NotFoundException;
import soccer.game.streetsoccermanager.exceptions.EntryNotValidException;
import soccer.game.streetsoccermanager.model.entities.UserEntity;

import java.util.List;

public interface IUserService {
    List<UserEntity> getAll();
    UserEntity get(Long id);
    UserEntity getByEmail(String email);
    Boolean delete(Long id);
    UserEntity add(UserEntity user) throws NotFoundException, EntryNotValidException;
    UserEntity update(UserEntity user);
    void deleteAll();
}
