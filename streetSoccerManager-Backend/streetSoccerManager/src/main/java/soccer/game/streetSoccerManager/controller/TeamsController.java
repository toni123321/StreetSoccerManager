package soccer.game.streetSoccerManager.controller;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.model.entities.CustomTeam;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IFormationService;
import soccer.game.streetSoccerManager.service.serviceInterfaces.ITeamService;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IUserService;
import soccer.game.streetSoccerManager.model.entities.Team;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("/teams")
public class TeamsController {
    @Qualifier("teamService")
    private ITeamService teamService;


    public TeamsController(ITeamService teamService) {
        this.teamService = teamService;
    }



    @GetMapping("{id}")
    public ResponseEntity<Team> getTeam(@PathVariable(value = "id") Long id) {
        Team team = teamService.get(id);

        if(team != null) {
            return ResponseEntity.ok().body(team);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams(
            @RequestParam(value = "isCustom") Optional<Boolean> isCustom)
    {
        List<Team> teams = null;
        if(isCustom.isPresent())
        {
            if(Boolean.TRUE.equals(isCustom.get())){
                teams = teamService.getCustomTeams();
            }
            else{
                teams = teamService.getOfficialTeams();
            }
        }
        else {
            teams = teamService.getAll();
        }

        if(teams != null) {
            return ResponseEntity.ok().body(teams);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping("/opponentsFriendlyMatch")
//    public ResponseEntity<List<Team>> getOpponentsForFriendlyMatch() {
//        List<Team> teams = null;
//        teams = teamService.getAll();
//
//        if(teams != null) {
//            return ResponseEntity.ok().body(teams);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }



    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable Long id) {
        if(Boolean.TRUE.equals(teamService.delete(id))) {
            // Idempotent method. Always return the same response (even if the resource has already been deleted before).
            return ResponseEntity.ok().body("Successfully deleted!");
        }
        return ResponseEntity.notFound().build();
    }


//    @PutMapping()
//    public ResponseEntity<Team> updateTeam(@RequestBody Team team) {
//        // Idempotent method. Always update (even if the resource has already been updated before).
//        if (teamService.update(team)) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return new ResponseEntity("Please provide a valid team id",HttpStatus.NOT_FOUND);
//        }
//    }
}
