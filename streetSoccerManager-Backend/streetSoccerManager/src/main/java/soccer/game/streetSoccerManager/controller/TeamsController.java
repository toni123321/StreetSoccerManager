package soccer.game.streetSoccerManager.controller;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.Interfaces.IService;
import soccer.game.streetSoccerManager.model.Formation;
import soccer.game.streetSoccerManager.model.Team;
import soccer.game.streetSoccerManager.model.User;
import soccer.game.streetSoccerManager.repository.FakeDatabase;
import soccer.game.streetSoccerManager.service.FormationService;
import soccer.game.streetSoccerManager.service.TeamService;
import soccer.game.streetSoccerManager.service.UserService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teams")
public class TeamsController {

    
    @Qualifier("teamService")
    private IService teamService;

    @Qualifier("userService")
    private IService userService;

    @Qualifier("formationService")
    private IService formationService;


    public TeamsController(IService teamService, IService userService, IService formationService) {
        this.teamService = teamService;
        this.userService = userService;
        this.formationService = formationService;
    }


    @GetMapping("{id}")
    public ResponseEntity<Team> getTeam(@PathVariable(value = "id") int id) {
        Team team = (Team) teamService.get(id);

        if(team != null) {
            return ResponseEntity.ok().body(team);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = null;
        teams = ((List<Team>) teamService.getAll());

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
        Team team = ((Team) teamService.get(id));
        if (team == null){
            return new ResponseEntity("Please provide a valid team id.",HttpStatus.NOT_FOUND);
        }

        User manager = ((User) userService.get(managerId));
        Formation formation = ((Formation) formationService.get(formationId));

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
