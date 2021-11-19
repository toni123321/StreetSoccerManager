package soccer.game.streetSoccerManager.controller;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
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
    private ModelMapper modelMapper;


    public TeamsController(ITeamService teamService) {
        this.teamService = teamService;
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }


    @GetMapping("{id}")
    public ResponseEntity<TeamDTO> getTeam(@PathVariable(value = "id") Long id) {
        Team teamEntity = teamService.get(id);
        TeamDTO teamDTO = modelMapper.map(teamEntity, TeamDTO.class);

        if(teamDTO != null) {
            return ResponseEntity.ok().body(teamDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TeamDTO>> getAllTeams(
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

        List<TeamDTO> teamDTOs = modelMapper.map(teams, new TypeToken<List<TeamDTO>>() {}.getType());
        if(teamDTOs != null) {
            return ResponseEntity.ok().body(teamDTOs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable Long id) {
        if(teamService.delete(id)) {
            return ResponseEntity.ok().body("Successfully deleted!");
        }
        return ResponseEntity.notFound().build();
    }

}
