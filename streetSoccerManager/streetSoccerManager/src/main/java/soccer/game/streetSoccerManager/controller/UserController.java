package soccer.game.streetSoccerManager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.model.Formation;
import soccer.game.streetSoccerManager.model.FrontendUser;
import soccer.game.streetSoccerManager.model.Team;
import soccer.game.streetSoccerManager.model.User;
import soccer.game.streetSoccerManager.service.FormationService;
import soccer.game.streetSoccerManager.service.TeamService;
import soccer.game.streetSoccerManager.service.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final TeamService teamService = new TeamService();

    private static final UserService userService = new UserService();

    private static final FormationService formationService = new FormationService();


    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") int id) {
        User user = userService.getUser(id);

        if(user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = null;
        users = userService.getAllUsers();

        if(users != null) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        // Idempotent method. Always return the same response (even if the resource has already been deleted before).
        return ResponseEntity.ok().build();

    }



}
