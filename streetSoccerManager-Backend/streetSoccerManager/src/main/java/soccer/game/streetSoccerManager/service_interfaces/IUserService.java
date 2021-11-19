package soccer.game.streetSoccerManager.service_interfaces;

import soccer.game.streetSoccerManager.model.entities.UserEntity;

import java.util.List;

public interface IUserService {
    List<UserEntity> getAll();
    UserEntity get(Long id);
    UserEntity getByEmail(String email);
    Boolean delete(Long id);
    UserEntity add(UserEntity user);
    UserEntity update(UserEntity user);
}
