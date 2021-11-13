package soccer.game.streetSoccerManager.service_interfaces;

import soccer.game.streetSoccerManager.model.dtos.UserDTO;

import java.util.List;

public interface IUserService {
    List<UserDTO> getAll();
    UserDTO get(Long id);
    UserDTO getByEmail(String email);
    Boolean delete(Long id);
    UserDTO add(UserDTO user);
    UserDTO update(UserDTO user);
}
