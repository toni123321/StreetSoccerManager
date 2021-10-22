package soccer.game.street_soccer_manager.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.street_soccer_manager.service.serviceInterfaces.IFormationService;
import soccer.game.street_soccer_manager.service.serviceInterfaces.ITeamService;
import soccer.game.street_soccer_manager.service.serviceInterfaces.IUserService;
import soccer.game.street_soccer_manager.model.User;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("/users")
public class UserController {

    @Qualifier("teamService")
    private ITeamService teamService;

    @Qualifier("userService")
    private IUserService userService;

    @Qualifier("formationService")
    private IFormationService formationService;

    public UserController(ITeamService teamService, IUserService userService, IFormationService formationService) {
        this.teamService = teamService;
        this.userService = userService;
        this.formationService = formationService;
    }


    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") Long id) {
        User user = userService.get(id);

        if(user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = null;
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



}
