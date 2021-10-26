package soccer.game.streetSoccerManager.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.model.entities.PlayerStats;
import soccer.game.streetSoccerManager.model.entities.Position;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IPlayerStatsService;

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
    public ResponseEntity<List<PlayerStats>> getAll() {
        List<PlayerStats> playerStats = null;

        playerStats = playerStatsService.getAll();

        if(playerStats != null) {
            return ResponseEntity.ok().body(playerStats);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<PlayerStats> createPlayerStats(@RequestBody PlayerStats playerStats) {
        if (!playerStatsService.add(playerStats)){
            String entity =  "Player stats with id " + playerStats.getId() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "playerStats" + "/" + playerStats.getId(); // url of the created student
            URI uri = URI.create(url);
            return new ResponseEntity(playerStats,HttpStatus.CREATED);
        }

    }



}
