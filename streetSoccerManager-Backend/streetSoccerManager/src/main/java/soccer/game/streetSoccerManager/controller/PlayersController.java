package soccer.game.streetSoccerManager.controller;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.model.converters.PlayerConverter;
import soccer.game.streetSoccerManager.model.dtos.PlayerDTO;
import soccer.game.streetSoccerManager.service_interfaces.IPlayerService;
import soccer.game.streetSoccerManager.model.entities.Player;


import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("/players")
public class PlayersController {

    @Qualifier("playerService")
    private IPlayerService playerService;


    public PlayersController(IPlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("{id}")
    public ResponseEntity<PlayerDTO> getPlayer(@PathVariable(value = "id") Long id) {
        PlayerDTO player = playerService.get(id);
        if(player != null) {
            return ResponseEntity.ok().body(player);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getAllPlayers(
            @RequestParam(value = "teamId") Optional<Long> teamId,
            @RequestParam(value = "starting") Optional<Boolean> starting) {
        List<PlayerDTO> players = null;
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
    public ResponseEntity<List<PlayerDTO>> getPlayersAvailableForSwapping(
            @RequestParam(value = "teamId") Optional<Long> teamId,
            @RequestParam(value = "playerToSwapId") Optional<Long> playerToSwapId) {
        List<PlayerDTO> players = null;

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
    public ResponseEntity<PlayerDTO> createPlayer(@RequestBody PlayerDTO player) {
        PlayerDTO createdPlayer = playerService.add(player);
        if (createdPlayer == null){
            String msg =  "Player with id " + player.getId() + " already exists.";
            return new ResponseEntity(msg, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity(createdPlayer,HttpStatus.CREATED);
        }
    }

    @PutMapping()
    public ResponseEntity<PlayerDTO> updatePlayer(@RequestBody PlayerDTO player) {
        PlayerDTO updatedPlayer = playerService.update(player);
        if(updatedPlayer != null) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid player id",HttpStatus.NOT_FOUND);
        }
    }

}
