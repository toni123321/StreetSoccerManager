package soccer.game.streetSoccerManager.controller;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.Interfaces.IService;
import soccer.game.streetSoccerManager.model.Formation;
import soccer.game.streetSoccerManager.model.Player;
import soccer.game.streetSoccerManager.model.Team;
import soccer.game.streetSoccerManager.model.User;
import soccer.game.streetSoccerManager.service.FormationService;
import soccer.game.streetSoccerManager.service.PlayerService;
import soccer.game.streetSoccerManager.service.TeamService;
import soccer.game.streetSoccerManager.service.UserService;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayersController {

    @Qualifier("playerService")
    private IService playerService;
    
    @Qualifier("teamService")
    private IService teamService;

    @Qualifier("userService")
    private IService userService;

    @Qualifier("formationService")
    private IService formationService;


    public PlayersController(IService playerService, IService teamService, IService userService, IService formationService) {
        this.playerService = playerService;
        this.teamService = teamService;
        this.userService = userService;
        this.formationService = formationService;
    }


    @GetMapping("{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable(value = "id") int id) {
        Player player = ((Player) playerService.get(id));

        if(player != null) {
            return ResponseEntity.ok().body(player);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = null;
        players = ((List<Player>) playerService.getAll());

        if(players != null) {
            return ResponseEntity.ok().body(players);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity deletePlayer(@PathVariable int id) {
        playerService.delete(id);
        // Idempotent method. Always return the same response (even if the resource has already been deleted before).
        return ResponseEntity.ok().build();

    }

    @PostMapping()
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        if (!playerService.add(player)){
            String entity =  "Player with id " + player.getId() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "player" + "/" + player.getId();
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }

    }

    @PutMapping()
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player) {
        // Idempotent method. Always update (even if the resource has already been updated before).
        if (playerService.update(player)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid player id",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable("id") int id, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("dob") Date dob, @RequestParam("price") double price, @RequestParam("team") int teamId) {
        Player player = ((Player) playerService.get(id));
        if (player == null){
            return new ResponseEntity("Please provide a valid player id.",HttpStatus.NOT_FOUND);
        }

        Team team = ((Team) teamService.get(teamId));

        if (team == null){
            return new ResponseEntity("Please provide a valid team id.",HttpStatus.BAD_REQUEST);
        }
        else {

            player.setFirstName(firstName);
            player.setLastName(lastName);
            player.setDob(dob);
            player.setPrice(price);
            player.setTeam(team);

            return ResponseEntity.noContent().build();
        }
    }
}
