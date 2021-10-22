package soccer.game.street_soccer_manager.controller;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.street_soccer_manager.model.CustomTeam;
import soccer.game.street_soccer_manager.service.serviceInterfaces.IFormationService;
import soccer.game.street_soccer_manager.service.serviceInterfaces.ITeamService;
import soccer.game.street_soccer_manager.service.serviceInterfaces.IUserService;
import soccer.game.street_soccer_manager.model.Team;


import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("/teams")
public class TeamsController {
    @Qualifier("teamService")
    private ITeamService teamService;

    @Qualifier("userService")
    private IUserService userService;

    @Qualifier("formationService")
    private IFormationService formationService;

    public TeamsController(ITeamService teamService, IUserService userService, IFormationService formationService) {
        this.teamService = teamService;
        this.userService = userService;
        this.formationService = formationService;
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
        if(teamService.delete(id)) {
            // Idempotent method. Always return the same response (even if the resource has already been deleted before).
            return ResponseEntity.ok().body("Successfully deleted!");
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<CustomTeam> createTeam(@RequestBody CustomTeam team) {
        if (!teamService.add(team)){
            String entity =  "Team with id " + team.getId() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "team" + "/" + team.getId(); // url of the created student
            URI uri = URI.create(url);
            return new ResponseEntity(team,HttpStatus.CREATED);
        }

    }

    @PutMapping()
    public ResponseEntity<Team> updateTeam(@RequestBody Team team) {
        // Idempotent method. Always update (even if the resource has already been updated before).
        if (teamService.update(team)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid team id",HttpStatus.NOT_FOUND);
        }
    }
}
