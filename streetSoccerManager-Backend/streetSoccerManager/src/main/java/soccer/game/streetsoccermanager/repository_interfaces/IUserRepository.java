package soccer.game.streetsoccermanager.repository_interfaces;

import soccer.game.streetsoccermanager.model.entities.UserEntity;

import java.util.List;

public interface IUserRepository {
    List<UserEntity> getAll();
    UserEntity get(Long id);
    UserEntity getByEmail(String email);
    Boolean delete(Long id);
    UserEntity add(UserEntity userEntity);
    UserEntity update(UserEntity userEntity);
    void deleteAll();
}
