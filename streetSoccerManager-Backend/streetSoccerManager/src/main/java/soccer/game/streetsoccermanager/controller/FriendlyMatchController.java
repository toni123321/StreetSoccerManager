package soccer.game.streetsoccermanager.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetsoccermanager.model.dtos.MatchDTO;
import soccer.game.streetsoccermanager.model.dtos.StartFriendlyMatchDTO;
import soccer.game.streetsoccermanager.model.entities.Match;
import soccer.game.streetsoccermanager.service_interfaces.IMatchService;

@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("/friendlyMatches")
public class FriendlyMatchController {
    @Qualifier("matchService")
    private IMatchService matchService;
    private ModelMapper modelMapper;

    public FriendlyMatchController(IMatchService matchService) {
        this.matchService = matchService;
        this.modelMapper = new ModelMapper();
    }

    @PostMapping
    public ResponseEntity<MatchDTO> playFriendlyMatch(@RequestBody StartFriendlyMatchDTO startMatchInfo){
        Match createdMatchEntity = matchService.playFriendlyMatch(startMatchInfo);
        MatchDTO createdMatchDTO = modelMapper.map(createdMatchEntity, MatchDTO.class);
        if (createdMatchDTO == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(createdMatchDTO,HttpStatus.CREATED);
        }
    }
}
