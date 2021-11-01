package soccer.game.streetSoccerManager.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.model.converters.EndUserConverter;
import soccer.game.streetSoccerManager.model.dtos.EndUserDTO;
import soccer.game.streetSoccerManager.model.entities.Admin;
import soccer.game.streetSoccerManager.model.entities.EndUser;
import soccer.game.streetSoccerManager.model.entities.User;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IFormationService;
import soccer.game.streetSoccerManager.service.serviceInterfaces.ITeamService;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IUserService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("/endUsers")
public class EndUserController {
    @Qualifier("userService")
    private IUserService userService;
    private EndUserConverter endUserConverter = new EndUserConverter();

    public EndUserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getEndUser(@PathVariable(value = "id") Long id) {

        EndUser user;
        if(userService.get(id) instanceof Admin){
            user = null;
        }
        else{
            user = (EndUser) userService.get(id);
        }


        if(user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllEndUsers() {
        List<User> users = null;
        users = userService.getAll().stream().filter(EndUser.class::isInstance).collect(Collectors.toList());
        if(users != null) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping()
    public ResponseEntity<EndUserDTO> createEndUser(@RequestBody EndUserDTO endUserDTO) {
        EndUser endUser = endUserConverter.convertEndUserDtoToEndUser(endUserDTO);
        if (!userService.add(endUser)){
            String entity =  "user with id " + endUser.getId() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            EndUserDTO endUserDTOtoReturn = endUserConverter.convertEndUserToEndUserDto(((EndUser) userService.get(endUser.getId())));
            return new ResponseEntity(endUserDTOtoReturn,HttpStatus.CREATED);
        }

    }

    @PutMapping()
    public ResponseEntity<EndUserDTO> updateEndUser(@RequestBody EndUserDTO endUserDTO) {
        // Idempotent method. Always update (even if the resource has already been updated before).
        EndUser endUser = endUserConverter.convertEndUserDtoToEndUser(endUserDTO);
        if (userService.update(endUser)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid user id",HttpStatus.NOT_FOUND);
        }
    }



    @PutMapping("{id}")
    public ResponseEntity<EndUser> updateEndUser(@PathVariable("id") Long id, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("nickname") String nickname, @RequestParam("points") double points) {
        EndUser user = (EndUser) userService.get(id);
        if (user == null){
            return new ResponseEntity("Please provide a valid user id.",HttpStatus.NOT_FOUND);
        }

        user.setEmail(email);
        user.setPassword(password);
        user.setNickname(nickname);
        user.setPoints(points);

        return ResponseEntity.noContent().build();

    }

}
