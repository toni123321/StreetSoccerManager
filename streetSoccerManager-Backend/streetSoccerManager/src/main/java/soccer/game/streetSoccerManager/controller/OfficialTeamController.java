package soccer.game.streetSoccerManager.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.model.converters.OfficialTeamConverter;
import soccer.game.streetSoccerManager.model.dtos.OfficialTeamDTO;
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
    private OfficialTeamConverter officialTeamConverter = new OfficialTeamConverter();

    public OfficialTeamController(ITeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping()
    public ResponseEntity<OfficialTeamDTO> createTeam(@RequestBody OfficialTeamDTO officialTeamDTO) {
        OfficialTeam officialTeam = officialTeamConverter.convertOfficialTeamDtoToOfficialTeam(officialTeamDTO);
        if (!teamService.add(officialTeam)){
            String entity =  "Team with id " + officialTeam.getId() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            OfficialTeamDTO officialTeamDTOtoReturn = officialTeamConverter.convertOfficialTeamToOfficialTeamDto(((OfficialTeam) teamService.get(officialTeam.getId())));
            return new ResponseEntity(officialTeamDTOtoReturn,HttpStatus.CREATED);
        }

    }
}
