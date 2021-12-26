package soccer.game.streetsoccermanager.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetsoccermanager.model.dtos.CustomTeamDTO;
import soccer.game.streetsoccermanager.model.entities.CustomTeam;
import soccer.game.streetsoccermanager.model.entities.Team;
import soccer.game.streetsoccermanager.service_interfaces.ITeamService;


@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("/customTeams")
public class CustomTeamController {

    private ITeamService teamService;
    private ModelMapper modelMapper;

    @Autowired
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
