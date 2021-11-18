package soccer.game.streetSoccerManager.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.model.converters.CustomTeamConverter;
import soccer.game.streetSoccerManager.model.dtos.CustomTeamDTO;
import soccer.game.streetSoccerManager.model.entities.CustomTeam;
import soccer.game.streetSoccerManager.service_interfaces.ITeamService;

@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("/customTeams")
public class CustomTeamController {
    @Qualifier("teamService")
    private ITeamService teamService;

    public CustomTeamController(ITeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping()
    public ResponseEntity<CustomTeamDTO> createCustomTeam(@RequestBody CustomTeamDTO customTeam) {
        CustomTeamDTO customTeamDTO = ((CustomTeamDTO) teamService.add(customTeam));
        if (customTeamDTO == null){
            String entity =  "Team with id " + customTeam.getId() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity(customTeamDTO,HttpStatus.CREATED);
        }

    }

}
