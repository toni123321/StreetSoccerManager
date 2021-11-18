package soccer.game.streetSoccerManager.controller;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.model.dtos.TeamDTO;
import soccer.game.streetSoccerManager.service_interfaces.ITeamService;
import soccer.game.streetSoccerManager.model.entities.Team;

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
    public ResponseEntity<TeamDTO> getTeam(@PathVariable(value = "id") Long id) {
        TeamDTO team = teamService.get(id);

        if(team != null) {
            return ResponseEntity.ok().body(team);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<TeamDTO>> getAllTeams(
            @RequestParam(value = "isCustom") Optional<Boolean> isCustom)
    {
        List<TeamDTO> teams = null;
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
    public ResponseEntity deleteTeam(@PathVariable Long id) {
        teamService.delete(id);
        return ResponseEntity.ok().build();
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
