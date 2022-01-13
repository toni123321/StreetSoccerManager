package soccer.game.streetsoccermanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetsoccermanager.model.entities.PlayerStats;
import soccer.game.streetsoccermanager.model.entities.Team;
import soccer.game.streetsoccermanager.service.RatingManager;
import soccer.game.streetsoccermanager.service_interfaces.IPlayerService;
import soccer.game.streetsoccermanager.service_interfaces.ITeamService;


@RestController
@RequestMapping("/ratings")
public class RatingController {

    private IPlayerService playerService;
    private ITeamService teamService;

    @Autowired
    public RatingController(IPlayerService playerService, ITeamService teamService) {
        this.playerService = playerService;
        this.teamService = teamService;
    }

    @GetMapping("/player/{id}")
    public ResponseEntity<Integer> getPlayerRating(@PathVariable(value = "id") Long id) {
        PlayerStats playerStats = playerService.get(id).getPlayerAdditionalInfo().getPlayerStats();
        if(playerStats != null) {
            Integer playerRating = RatingManager.calcPlayerOverallRating(playerStats);
            return ResponseEntity.ok().body(playerRating);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/team/{id}")
    public ResponseEntity<Integer> getTeamRating(@PathVariable(value = "id") Long id) {
        Team team = teamService.get(id);
        if(team != null) {
            Integer teamRating = RatingManager.calcTeamOverallRating(team);
            return ResponseEntity.ok().body(teamRating);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
