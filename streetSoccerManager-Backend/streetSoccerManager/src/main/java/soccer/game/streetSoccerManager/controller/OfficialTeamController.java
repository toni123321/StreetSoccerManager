package soccer.game.streetSoccerManager.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.model.entities.CustomTeam;
import soccer.game.streetSoccerManager.model.entities.OfficialTeam;
import soccer.game.streetSoccerManager.service.serviceInterfaces.ITeamService;

import java.net.URI;

@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("/officialTeams")
public class OfficialTeamController {
    @Qualifier("teamService")
    private ITeamService teamService;


    public OfficialTeamController(ITeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping()
    public ResponseEntity<OfficialTeam> createTeam(@RequestBody OfficialTeam team) {
        if (Boolean.FALSE.equals(teamService.add(team))){
            String entity =  "Team with id " + team.getId() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "team" + "/" + team.getId(); // url of the created student
            URI uri = URI.create(url);
            return new ResponseEntity(team,HttpStatus.CREATED);
        }

    }
}
