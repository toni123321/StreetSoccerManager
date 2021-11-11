package soccer.game.streetSoccerManager.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.model.dtos.UserDTO;
import soccer.game.streetSoccerManager.service_interfaces.IFormationService;
import soccer.game.streetSoccerManager.service_interfaces.ITeamService;
import soccer.game.streetSoccerManager.service_interfaces.IUserService;
import soccer.game.streetSoccerManager.model.entities.User;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("/users")
public class UserController {
    @Qualifier("userService")
    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }


    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable(value = "id") Long id) {
        UserDTO user = userService.get(id);
        if(user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = null;
        users = userService.getAll();

        if(users != null) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.delete(id);
        // Idempotent method. Always return the same response (even if the resource has already been deleted before).
        return ResponseEntity.ok().build();

    }

    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.add(userDTO);
        if (createdUser == null){
            String entity =  "user with id " + userDTO.getId() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity(userDTO,HttpStatus.CREATED);
        }
    }

//    @PutMapping()
//    public ResponseEntity<UserDTO> updateUser(@RequestBody AdminDTO adminDTO) {
//        // Idempotent method. Always update (even if the resource has already been updated before).
//        Admin admin = adminConverter.convertAdminDtoToAdmin(adminDTO);
//        if (Boolean.TRUE.equals(userService.update(admin))) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return new ResponseEntity("Please provide a valid user id",HttpStatus.NOT_FOUND);
//        }
//    }



}
