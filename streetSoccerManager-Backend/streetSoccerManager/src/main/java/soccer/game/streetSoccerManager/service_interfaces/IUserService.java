package soccer.game.streetSoccerManager.service_interfaces;

import soccer.game.streetSoccerManager.model.dtos.UserDTO;
import soccer.game.streetSoccerManager.model.entities.User;

import java.util.List;

public interface IUserService {
    List<UserDTO> getAll();
    UserDTO get(Long id);
    Boolean delete(Long id);
    Boolean add(UserDTO user);
    Boolean update(UserDTO user);
}
