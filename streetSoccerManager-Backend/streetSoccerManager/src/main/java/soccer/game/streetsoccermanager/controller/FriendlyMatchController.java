package soccer.game.streetsoccermanager.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetsoccermanager.model.dtos.*;
import soccer.game.streetsoccermanager.model.entities.FriendlyMatch;
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

    @GetMapping("{id}")
    public ResponseEntity<MatchDTO> getMatch(@PathVariable(value = "id") Long id) {
        Match matchEntity = matchService.get(id);
        MatchDTO matchDTO = modelMapper.map(matchEntity, MatchDTO.class);
        if(matchDTO != null) {
            return ResponseEntity.ok().body(matchDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/startMatch")
    public ResponseEntity<FriendlyMatchDTO> startFriendlyMatch(@RequestBody StartFriendlyMatchDTO startMatchInfo){
        FriendlyMatch inputtedMatchEntity = modelMapper.map(startMatchInfo, FriendlyMatch.class);
        Match createdMatchEntity = matchService.add(inputtedMatchEntity);
        FriendlyMatchDTO createdMatchDTO = modelMapper.map(createdMatchEntity, FriendlyMatchDTO.class);
        if (createdMatchDTO == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(createdMatchDTO,HttpStatus.CREATED);
        }
    }

    @PutMapping("/playMatch")
    public ResponseEntity<FriendlyMatchDTO> playFriendlyMatch(@RequestBody PlayFriendlyMatchDTO matchDTO){
        Match updatedMatchEntity = matchService.playFriendlyMatch(matchDTO.getFriendlyMatch().getId(), matchDTO.getCommand());

        if (updatedMatchEntity == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            FriendlyMatchDTO updatedMatchDTO = modelMapper.map(updatedMatchEntity, FriendlyMatchDTO.class);
            return new ResponseEntity<>(updatedMatchDTO,HttpStatus.CREATED);
        }
    }


}
