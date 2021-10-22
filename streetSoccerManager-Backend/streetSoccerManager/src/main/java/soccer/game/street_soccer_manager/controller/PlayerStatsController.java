package soccer.game.street_soccer_manager.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.street_soccer_manager.model.PlayerStats;
import soccer.game.street_soccer_manager.service.serviceInterfaces.IPlayerStatsService;

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



}
