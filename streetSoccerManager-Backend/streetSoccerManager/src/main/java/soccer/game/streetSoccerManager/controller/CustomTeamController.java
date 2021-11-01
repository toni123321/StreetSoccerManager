package soccer.game.streetSoccerManager.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.model.converters.CustomTeamConverter;
import soccer.game.streetSoccerManager.model.dtos.CustomTeamDTO;
import soccer.game.streetSoccerManager.model.entities.CustomTeam;
import soccer.game.streetSoccerManager.service.serviceInterfaces.ITeamService;

import java.net.URI;

@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("/customTeams")
public class CustomTeamController {
    @Qualifier("teamService")
    private ITeamService teamService;
    private CustomTeamConverter customTeamConverter = new CustomTeamConverter();

    public CustomTeamController(ITeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping()
    public ResponseEntity<CustomTeamDTO> createCustomTeam(@RequestBody CustomTeamDTO customTeamDTO) {
        CustomTeam customTeam = customTeamConverter.convertCustomTeamDtoToCustomTeam(customTeamDTO);
        if (!teamService.add(customTeam)){
            String entity =  "Team with id " + customTeam.getId() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            CustomTeamDTO customTeamDTOToReturn = customTeamConverter.convertCustomTeamToCustomTeamDto(((CustomTeam) teamService.get(customTeam.getId())));
            return new ResponseEntity(customTeamDTOToReturn,HttpStatus.CREATED);
        }

    }

}
