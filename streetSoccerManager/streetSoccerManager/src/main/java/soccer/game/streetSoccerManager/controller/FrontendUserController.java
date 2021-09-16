package soccer.game.streetSoccerManager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.model.*;
import soccer.game.streetSoccerManager.service.FormationService;
import soccer.game.streetSoccerManager.service.TeamService;
import soccer.game.streetSoccerManager.service.UserService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/frontendUsers")
public class FrontendUserController {
    private static final TeamService teamService = new TeamService();

    private static final UserService userService = new UserService();

    private static final FormationService formationService = new FormationService();


    @GetMapping("{id}")
    public ResponseEntity<User> getFrontendUser(@PathVariable(value = "id") int id) {

        FrontendUser user;
        if(userService.getUser(id) instanceof Admin){
            user = null;
        }
        else{
            user = (FrontendUser) userService.getUser(id);
        }


        if(user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllFrontendUsers() {
        List<User> users = null;
        users = userService.getAllUsers().stream().filter(user -> user instanceof FrontendUser).collect(Collectors.toList());

        if(users != null) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping()
    public ResponseEntity<FrontendUser> createFrontendUser(@RequestBody FrontendUser user) {
        if (!userService.addUser(user)){
            String entity =  "user with id " + user.getId() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "user" + "/" + user.getId(); // url of the created student
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }

    }

    @PutMapping()
    public ResponseEntity<FrontendUser> updateFrontendUser(@RequestBody FrontendUser user) {
        // Idempotent method. Always update (even if the resource has already been updated before).
        if (userService.updateUser(user)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid user id",HttpStatus.NOT_FOUND);
        }
    }



    @PutMapping("{id}")
    public ResponseEntity<FrontendUser> updateFrontendUser(@PathVariable("id") int id,  @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("nickname") String nickname, @RequestParam("points") double points) {
        FrontendUser user = (FrontendUser) userService.getUser(id);
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
