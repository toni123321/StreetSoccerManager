package soccer.game.streetSoccerManager.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.model.dtos.UserDTO;
import soccer.game.streetSoccerManager.model.entities.UserEntity;
import soccer.game.streetSoccerManager.service_interfaces.IUserService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("/users")
public class UserController {
    @Qualifier("userService")
    private IUserService userService;
    private ModelMapper modelMapper;

    public UserController(IUserService userService) {
        this.userService = userService;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") Long id) {
        UserEntity userEntity = userService.get(id);
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
        if(userDTO != null) {
            return ResponseEntity.ok().body(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable(value = "email") String email) {
        UserEntity userEntity = userService.getByEmail(email);
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
        if(userDTO != null) {
            return ResponseEntity.ok().body(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserEntity> userEntities = userService.getAll();
        List<UserDTO> userDTOs = modelMapper.map(userEntities, new TypeToken<List<UserDTO>>() {}.getType());

        if(userDTOs != null) {
            return ResponseEntity.ok().body(userDTOs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        if(Boolean.TRUE.equals(userService.delete(id))) {
            return ResponseEntity.ok().body("Successfully deleted!");
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserEntity userEntityInput = modelMapper.map(userDTO, UserEntity.class);
        UserEntity createdUserEntity = userService.add(userEntityInput);
        UserDTO createdUserDTO = modelMapper.map(createdUserEntity, UserDTO.class);
        if (createdUserDTO == null){
            String entity =  "User with id " + userDTO.getId() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity(createdUserDTO,HttpStatus.CREATED);
        }
    }

    @PutMapping()
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        UserEntity inputtedUserEntity = modelMapper.map(userDTO, UserEntity.class);
        UserEntity updatedUserEntity = userService.update(inputtedUserEntity);
        UserDTO updatedUserDTO = modelMapper.map(updatedUserEntity, UserDTO.class);
        if (updatedUserDTO == null){
            return new ResponseEntity("Please provide a valid user id",HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(updatedUserDTO,HttpStatus.OK);
        }
    }



}
