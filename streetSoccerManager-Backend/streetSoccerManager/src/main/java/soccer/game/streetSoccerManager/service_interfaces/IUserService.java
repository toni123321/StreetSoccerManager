package soccer.game.streetSoccerManager.service_interfaces;

import soccer.game.streetSoccerManager.model.dtos.UserDTO;
import soccer.game.streetSoccerManager.model.entities.User;

import java.util.List;

public interface IUserService {
    List<UserDTO> getAll();
    UserDTO get(Long id);
    Boolean delete(Long id);
    UserDTO add(UserDTO user);
    UserDTO update(UserDTO user);
}
