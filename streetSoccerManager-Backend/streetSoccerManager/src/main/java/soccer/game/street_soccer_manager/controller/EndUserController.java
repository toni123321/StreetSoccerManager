package soccer.game.street_soccer_manager.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.street_soccer_manager.service.serviceInterfaces.IFormationService;
import soccer.game.street_soccer_manager.service.serviceInterfaces.ITeamService;
import soccer.game.street_soccer_manager.service.serviceInterfaces.IUserService;
import soccer.game.street_soccer_manager.model.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("/endUsers")
public class EndUserController {
    @Qualifier("teamService")
    private ITeamService teamService;

    @Qualifier("userService")
    private IUserService userService;

    @Qualifier("formationService")
    private IFormationService formationService;

    public EndUserController(ITeamService teamService, IUserService userService, IFormationService formationService) {
        this.teamService = teamService;
        this.userService = userService;
        this.formationService = formationService;
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
    public ResponseEntity<EndUser> createEndUser(@RequestBody EndUser user) {
        if (!userService.add(user)){
            String entity =  "user with id " + user.getId() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "user" + "/" + user.getId(); // url of the created student
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }

    }

    @PutMapping()
    public ResponseEntity<EndUser> updateEndUser(@RequestBody EndUser user) {
        // Idempotent method. Always update (even if the resource has already been updated before).
        if (userService.update(user)) {
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
