package soccer.game.streetSoccerManager.controller;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    private ModelMapper modelMapper;


    public PlayersController(IPlayerService playerService) {
        this.playerService = playerService;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping("{id}")
    public ResponseEntity<PlayerDTO> getPlayer(@PathVariable(value = "id") Long id) {
        Player playerEntity = playerService.get(id);
        PlayerDTO playerDTO = modelMapper.map(playerEntity, PlayerDTO.class);
        if(playerDTO != null) {
            return ResponseEntity.ok().body(playerDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getAllPlayers(
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

        List<PlayerDTO> playerDTOs =  modelMapper.map(players, new TypeToken<List<PlayerDTO>>() {}.getType());
        if(players != null) {
            return ResponseEntity.ok().body(playerDTOs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/availableForSwapping")
    public ResponseEntity<List<PlayerDTO>> getPlayersAvailableForSwapping(
            @RequestParam(value = "teamId") Optional<Long> teamId,
            @RequestParam(value = "playerToSwapId") Optional<Long> playerToSwapId) {
        List<Player> players = null;

        if(teamId.isPresent() && playerToSwapId.isPresent()){
            players = playerService.getAllPlayersInTeamAvailableForSwapping(teamId.get(), playerToSwapId.get());
        }

        List<PlayerDTO> playerDTOs = modelMapper.map(players, new TypeToken<List<PlayerDTO>>() {}.getType());
        if(players != null) {
            return ResponseEntity.ok().body(playerDTOs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity deletePlayer(@PathVariable Long id) {
        if(Boolean.TRUE.equals(playerService.delete(id))) {
            return ResponseEntity.ok().body("Successfully deleted!");
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<PlayerDTO> createPlayer(@RequestBody PlayerDTO player) {
        Player inputtedPlayerEntity = modelMapper.map(player, Player.class);
        Player createdPlayerEntity = playerService.add(inputtedPlayerEntity);
        PlayerDTO createdPlayerDTO = modelMapper.map(createdPlayerEntity, PlayerDTO.class);
        if (createdPlayerDTO == null){
            String msg =  "Player with id " + player.getId() + " already exists.";
            return new ResponseEntity(msg, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity(createdPlayerDTO,HttpStatus.CREATED);
        }
    }

    @PutMapping()
    public ResponseEntity<PlayerDTO> updatePlayer(@RequestBody PlayerDTO player) {
        Player inputtedPlayerEntity = modelMapper.map(player, Player.class);
        Player updatedPlayerEntity = playerService.add(inputtedPlayerEntity);
        PlayerDTO updatedPlayerDTO = modelMapper.map(updatedPlayerEntity, PlayerDTO.class);
        if (updatedPlayerDTO == null){
            return new ResponseEntity("Please provide a valid player id", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(updatedPlayerDTO,HttpStatus.CREATED);
        }
    }

}
