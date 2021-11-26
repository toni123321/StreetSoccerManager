package soccer.game.streetSoccerManager.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.model.dtos.CustomTeamDTO;
import soccer.game.streetSoccerManager.model.entities.CustomTeam;
import soccer.game.streetSoccerManager.model.entities.Team;
import soccer.game.streetSoccerManager.service_interfaces.ITeamService;


@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("/customTeams")
public class CustomTeamController {
    @Qualifier("teamService")
    private ITeamService teamService;
    private ModelMapper modelMapper;

    public CustomTeamController(ITeamService teamService) {
        this.teamService = teamService;
        this.modelMapper = new ModelMapper();
    }

    @PostMapping()
    public ResponseEntity<CustomTeamDTO> createCustomTeam(@RequestBody CustomTeamDTO customTeam) {
        CustomTeam inputtedCustomTeamEntity = modelMapper.map(customTeam, CustomTeam.class);
        Team createdCustomTeamEntity = teamService.add(inputtedCustomTeamEntity);
        CustomTeamDTO createdCustomTeamDTO = modelMapper.map(createdCustomTeamEntity, CustomTeamDTO.class);
        if (createdCustomTeamDTO == null){
            String msg =  "Custom team with id " + customTeam.getId() + " already exists.";
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(createdCustomTeamDTO,HttpStatus.CREATED);
        }
    }

    @PutMapping()
    public ResponseEntity<CustomTeamDTO> updateCustomTeam(@RequestBody CustomTeamDTO customTeam) {
        CustomTeam inputtedCustomTeamEntity = modelMapper.map(customTeam, CustomTeam.class);
        Team updatedCustomTeamEntity = teamService.update(inputtedCustomTeamEntity);
        CustomTeamDTO updatedCustomTeamDTO = modelMapper.map(updatedCustomTeamEntity, CustomTeamDTO.class);
        if (updatedCustomTeamDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(updatedCustomTeamDTO,HttpStatus.OK);
        }
    }



}
