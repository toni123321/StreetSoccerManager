package soccer.game.streetSoccerManager.controller;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.interfaces.serviceInterfaces.IFormationService;
import soccer.game.streetSoccerManager.interfaces.serviceInterfaces.ITeamService;
import soccer.game.streetSoccerManager.interfaces.serviceInterfaces.IUserService;
import soccer.game.streetSoccerManager.model.Formation;
import soccer.game.streetSoccerManager.model.Team;
import soccer.game.streetSoccerManager.model.User;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<Team> getTeam(@PathVariable(value = "id") int id) {
        Team team = teamService.get(id);

        if(team != null) {
            return ResponseEntity.ok().body(team);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = null;
        teams = teamService.getAll();

        if(teams != null) {
            return ResponseEntity.ok().body(teams);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity deleteTeam(@PathVariable int id) {
        teamService.delete(id);
        // Idempotent method. Always return the same response (even if the resource has already been deleted before).
        return ResponseEntity.ok().build();

    }

    @PostMapping()
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        if (!teamService.add(team)){
            String entity =  "Team with id " + team.getId() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "team" + "/" + team.getId(); // url of the created student
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
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

    @PutMapping("{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable("id") int id,  @RequestParam("name") String name, @RequestParam("formation") int formationId, @RequestParam("manager") int managerId) {
        Team team = teamService.get(id);
        if (team == null){
            return new ResponseEntity("Please provide a valid team id.",HttpStatus.NOT_FOUND);
        }

        User manager = userService.get(managerId);
        Formation formation = formationService.get(formationId);

        if (manager == null){
            return new ResponseEntity("Please provide a valid manager id.",HttpStatus.BAD_REQUEST);
        }
        else if(formation == null){
            return new ResponseEntity("Please provide a valid formation id.",HttpStatus.BAD_REQUEST);
        }
        else {

            team.setName(name);
            team.setFormation(formation);
            team.setManager(manager);
            return ResponseEntity.noContent().build();
        }
    }


}
