package soccer.game.streetsoccermanager.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    private IMatchService matchService;
    private ModelMapper modelMapper;

    @Autowired
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
    public ResponseEntity<PlayFriendlyMatchResponseDTO> playFriendlyMatch(@RequestBody PlayFriendlyMatchInputDTO matchDTO){
        Match match = matchService.get(matchDTO.getId());
        if(match == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(match.getCurrentMinute() < 30){
            Match updatedMatchEntityUserTurn = matchService.playFriendlyMatch(matchDTO.getId(), matchDTO.getCommand());
            Match updatedMatchEntity;
            if(updatedMatchEntityUserTurn.getCurrentMinute() < 30) {
                updatedMatchEntity = matchService.playFriendlyMatch(updatedMatchEntityUserTurn.getId(), "OPPONENT");
            }
            else{
                updatedMatchEntity = updatedMatchEntityUserTurn;
            }

            if (updatedMatchEntity == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                PlayFriendlyMatchResponseDTO playFriendlyMatchResponseDTO = modelMapper.map(updatedMatchEntity, PlayFriendlyMatchResponseDTO.class);
                Boolean isMatchEnds = updatedMatchEntity.getCurrentMinute() == 30;
                playFriendlyMatchResponseDTO.setIsMatchEnd(isMatchEnds);

                return new ResponseEntity<>(playFriendlyMatchResponseDTO,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }


}
