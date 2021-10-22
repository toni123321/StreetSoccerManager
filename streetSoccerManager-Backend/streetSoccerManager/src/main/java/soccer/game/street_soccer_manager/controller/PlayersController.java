package soccer.game.street_soccer_manager.controller;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.street_soccer_manager.service.serviceInterfaces.IFormationService;
import soccer.game.street_soccer_manager.service.serviceInterfaces.IPlayerService;
import soccer.game.street_soccer_manager.service.serviceInterfaces.ITeamService;
import soccer.game.street_soccer_manager.service.serviceInterfaces.IUserService;
import soccer.game.street_soccer_manager.model.Player;


import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("/players")
public class PlayersController {

    @Qualifier("playerService")
    private IPlayerService playerService;

    @Qualifier("teamService")
    private ITeamService teamService;

    @Qualifier("userService")
    private IUserService userService;

    @Qualifier("formationService")
    private IFormationService formationService;

    public PlayersController(IPlayerService playerService, ITeamService teamService, IUserService userService, IFormationService formationService) {
        this.playerService = playerService;
        this.teamService = teamService;
        this.userService = userService;
        this.formationService = formationService;
    }


    @GetMapping("{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable(value = "id") Long id) {
        Player player = playerService.get(id);

        if(player != null) {
            return ResponseEntity.ok().body(player);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers(
            @RequestParam(value = "teamId") Optional<Long> teamId,
            @RequestParam(value = "starting") Optional<Boolean> starting) {
        List<Player> players = null;

        if(teamId.isPresent()){
            if(starting.isPresent())
            {
                players = (starting.get()) ?
                        playerService.getStartingPlayers(teamId.get())
                        : playerService.getReserves(teamId.get());
            }
            else {
                players = playerService.getAllPlayersInTeam(teamId.get());
            }
        }
        else{
            players = playerService.getAll();
        }

        if(players != null) {
            return ResponseEntity.ok().body(players);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/availableForSwapping")
    public ResponseEntity<List<Player>> getPlayersAvailableForSwapping(
            @RequestParam(value = "teamId") Optional<Long> teamId,
            @RequestParam(value = "playerToSwapId") Optional<Long> playerToSwapId) {
        List<Player> players = null;

        if(teamId.isPresent() && playerToSwapId.isPresent()){
            players = playerService.getAllPlayersInTeamAvailableForSwapping(teamId.get(), playerToSwapId.get());
        }

        if(players != null) {
            return ResponseEntity.ok().body(players);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity deletePlayer(@PathVariable Long id) {
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

}
