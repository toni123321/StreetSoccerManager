package soccer.game.streetSoccerManager.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.model.dtos.UserDTO;
import soccer.game.streetSoccerManager.repository_interfaces.IUserRepository;
import soccer.game.streetSoccerManager.service_interfaces.IUserService;
import soccer.game.streetSoccerManager.model.entities.User;

import java.util.List;

@Service
public class UserService implements IUserService {

    private IUserRepository dataStore;
    private ModelMapper modelMapper = new ModelMapper();

    public UserService(@Qualifier("userJPADatabase") IUserRepository dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public List<UserDTO> getAll() {
        List<User> users = dataStore.getAll();
        List<UserDTO> usersDTO = modelMapper.map(users, new TypeToken<List<UserDTO>>() {}.getType());
        return usersDTO;
    }

    @Override
    public UserDTO get(Long id) {
        User user = dataStore.get(id);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    @Override
    public Boolean delete(Long id) {
        return dataStore.delete(id);
    }


    @Override
    public UserDTO add(UserDTO user) {
        User userInputEntity = modelMapper.map(user, User.class);
        User userOutputEntity = dataStore.add(userInputEntity);
        if(userOutputEntity != null) {
            UserDTO userOutputDTO = modelMapper.map(userOutputEntity, UserDTO.class);
            return userOutputDTO;
        }
        return null;
    }

    @Override
    public UserDTO update(UserDTO user) {
        User userInputEntity = modelMapper.map(user, User.class);
        User userOutputEntity = dataStore.update(userInputEntity);
        if(userOutputEntity != null) {
            UserDTO userOutputDTO = modelMapper.map(userOutputEntity, UserDTO.class);
            return userOutputDTO;
        }
        return null;
    }
}
