package soccer.game.streetSoccerManager.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    private static final TeamService teamService = new TeamService();

    private static final UserService userService = new UserService();

    private static final FormationService formationService = new FormationService();


    @GetMapping("{id}")
    public ResponseEntity<Team> getTeam(@PathVariable(value = "id") int id) {
        Team team = teamService.getTeam(id);

        if(team != null) {
            return ResponseEntity.ok().body(team);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = null;
        teams = teamService.getAllTeams();

        if(teams != null) {
            return ResponseEntity.ok().body(teams);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity deleteTeam(@PathVariable int id) {
        teamService.deleteTeam(id);
        // Idempotent method. Always return the same response (even if the resource has already been deleted before).
        return ResponseEntity.ok().build();

    }

    @PostMapping()
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        if (!teamService.addTeam(team)){
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
        if (teamService.updateTeam(team)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid team id",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable("id") int id,  @RequestParam("name") String name, @RequestParam("formation") int formationId, @RequestParam("manager") int managerId) {
        Team team = teamService.getTeam(id);
        if (team == null){
            return new ResponseEntity("Please provide a valid team id.",HttpStatus.NOT_FOUND);
        }

        User manager = userService.getUser(managerId);
        Formation formation = formationService.getFormation(formationId);

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
