package soccer.game.streetSoccerManager.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.model.dtos.MatchDTO;
import soccer.game.streetSoccerManager.model.dtos.StartFriendlyMatchDTO;
import soccer.game.streetSoccerManager.model.entities.FriendlyMatch;
import soccer.game.streetSoccerManager.model.entities.Match;
import soccer.game.streetSoccerManager.service_interfaces.IMatchService;

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
    public ResponseEntity<FriendlyMatch> playFriendlyMatch(@RequestBody StartFriendlyMatchDTO startMatchInfo){
        Match createdMatchEntity = matchService.playFriendlyMatch(startMatchInfo);
        MatchDTO createdMatchDTO = modelMapper.map(createdMatchEntity, MatchDTO.class);
        if (createdMatchDTO == null){
            String msg =  "Match already exists.";
            return new ResponseEntity(msg, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity(createdMatchDTO,HttpStatus.CREATED);
        }
    }
}
