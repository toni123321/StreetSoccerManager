package soccer.game.streetSoccerManager.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.model.dtos.UserDTO;
import soccer.game.streetSoccerManager.model.entities.UserEntity;
import soccer.game.streetSoccerManager.repository_interfaces.IUserRepository;
import soccer.game.streetSoccerManager.service_interfaces.IUserService;

import java.util.List;

@Service
public class UserService implements IUserService {

    private IUserRepository dataStore;
    private ModelMapper modelMapper = new ModelMapper();
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(@Qualifier("userJPADatabase") IUserRepository dataStore) {
        this.dataStore = dataStore;
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public List<UserDTO> getAll() {
        List<UserEntity> userEntities = dataStore.getAll();
        List<UserDTO> usersDTO = modelMapper.map(userEntities, new TypeToken<List<UserDTO>>() {}.getType());
        return usersDTO;
    }

    @Override
    public UserDTO get(Long id) {
        UserEntity userEntity = dataStore.get(id);
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
        return userDTO;
    }

    @Override
    public UserDTO getByEmail(String email){
        UserEntity userEntity = dataStore.getByEmail(email);
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
        return userDTO;
    }

    @Override
    public Boolean delete(Long id) {
        return dataStore.delete(id);
    }


    @Override
    public UserDTO add(UserDTO user) {
        UserEntity userEntityInputEntity = modelMapper.map(user, UserEntity.class);
        userEntityInputEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity userEntityOutputEntity = dataStore.add(userEntityInputEntity);
        if(userEntityOutputEntity != null) {
            UserDTO userOutputDTO = modelMapper.map(userEntityOutputEntity, UserDTO.class);
            return userOutputDTO;
        }
        return null;
    }

    @Override
    public UserDTO update(UserDTO user) {
        UserEntity userEntityInputEntity = modelMapper.map(user, UserEntity.class);
        UserEntity userEntityOutputEntity = dataStore.update(userEntityInputEntity);
        if(userEntityOutputEntity != null) {
            UserDTO userOutputDTO = modelMapper.map(userEntityOutputEntity, UserDTO.class);
            return userOutputDTO;
        }
        return null;
    }
}
