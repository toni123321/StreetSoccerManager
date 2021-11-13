package soccer.game.streetSoccerManager.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.model.converters.PlayerStatsConverter;
import soccer.game.streetSoccerManager.model.dtos.PlayerStatsDTO;
import soccer.game.streetSoccerManager.model.entities.PlayerStats;
import soccer.game.streetSoccerManager.service_interfaces.IPlayerStatsService;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("/playerStats")
public class PlayerStatsController {
    // todo: implement controller logic

    @Qualifier("playerStatsService")
    private IPlayerStatsService playerStatsService;

    public PlayerStatsController(IPlayerStatsService playerStatsService) {
        this.playerStatsService = playerStatsService;
    }

    @GetMapping
    public ResponseEntity<List<PlayerStatsDTO>> getAll() {
        List<PlayerStatsDTO> playerStats = playerStatsService.getAll();
        if(playerStats != null) {
            return ResponseEntity.ok().body(playerStats);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<PlayerStatsDTO> createPlayerStats(@RequestBody PlayerStatsDTO playerStats) {
        PlayerStatsDTO createdPlayerStats = playerStatsService.add(playerStats);
        if (createdPlayerStats == null){
            String msg =  "Player stats with id " + playerStats.getId() + " already exists.";
            return new ResponseEntity(msg, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity(createdPlayerStats,HttpStatus.CREATED);
        }

    }
}
