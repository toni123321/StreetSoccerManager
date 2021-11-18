package soccer.game.streetSoccerManager.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.model.dtos.CustomTeamDTO;
import soccer.game.streetSoccerManager.model.dtos.OfficialTeamDTO;
import soccer.game.streetSoccerManager.model.dtos.TeamDTO;
import soccer.game.streetSoccerManager.model.entities.OfficialTeam;
import soccer.game.streetSoccerManager.service_interfaces.ITeamService;

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
    public ResponseEntity<TeamDTO> createTeam(@RequestBody OfficialTeamDTO officialTeam) {
        TeamDTO officialTeamDTO = teamService.add(officialTeam);
        if (officialTeamDTO == null){
            String entity =  "Team with id " + officialTeam.getId() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity(officialTeamDTO,HttpStatus.CREATED);
        }
    }


}
