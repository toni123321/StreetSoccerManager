package soccer.game.streetSoccerManager.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.model.dtos.FriendlyMatchDTO;
import soccer.game.streetSoccerManager.model.entities.*;
import soccer.game.streetSoccerManager.service_interfaces.IMatchService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("/friendlyMatches")
public class FriendlyMatchController {
    private ModelMapper modelMapper = new ModelMapper();
    @Qualifier("matchService")
    private IMatchService matchService;

    public FriendlyMatchController(IMatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public ResponseEntity<List<Match>> getAllFriendlyMatches() {
        List<Match> friendlyMatches = null;
        friendlyMatches = matchService.getAll().stream().filter(FriendlyMatch.class::isInstance).collect(Collectors.toList());
        if(friendlyMatches != null) {
            return ResponseEntity.ok().body(friendlyMatches);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @RequestMapping("/matchesInfo")
//    @GetMapping
//    public ResponseEntity<List<MatchInfo>> getAllMatchesInfo() {
//        List<MatchInfo> matchesInfo = null;
//        matchesInfo = matchInfoService.getAll();
//        if(matchesInfo != null) {
//            return ResponseEntity.ok().body(matchesInfo);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

//    @PostMapping()
//    public ResponseEntity<FriendlyMatchDTO> playFriendlyMatch(@RequestBody MatchInfoDTO matchInfoDTO) {
//        modelMapper.getConfiguration().setAmbiguityIgnored(true);
//
//        MatchInfo matchInfo = modelMapper.map(matchInfoDTO, MatchInfo.class);
//
//        //FriendlyMatch friendlyMatch = modelMapper.map(friendlyMatchDTO, FriendlyMatch.class);
//        if (!matchInfoService.add(matchInfo)){
//            String entity =  "MatchInfo with id " + matchInfo.getId() + " already exists.";
//            return new ResponseEntity(entity, HttpStatus.CONFLICT);
//        }
////        MatchInfoDTO matchInfoDTOtoReturn = modelMapper.map(matchInfo, MatchInfoDTO.class);
////        return new ResponseEntity(matchInfoDTOtoReturn,HttpStatus.CREATED);
//        //FriendlyMatchDTO friendlyMatchDTOtoReturn = modelMapper.map(matchService.get(friendlyMatch.getId()), FriendlyMatchDTO.class);
//        // save match statistic
//        if(!matchStatisticService.playMatch(matchInfoDTO.getHomeTeam(), matchInfoDTO.getAwayTeam())){
//            String entity =  "MatchStatistic not created";
//            return new ResponseEntity(entity, HttpStatus.CONFLICT);
//        }
//        FriendlyMatch friendlyMatch = new FriendlyMatch(null,
//                matchInfoService.getAll().get(matchInfoService.getAll().size() - 1),
//                matchStatisticService.getAll().get(matchStatisticService.getAll().size() - 1));
//        if(!matchService.add(friendlyMatch)){
//            String entity =  "Match with id " + friendlyMatch.getId() + " already exists.";
//            return new ResponseEntity(entity, HttpStatus.CONFLICT);
//        }
//        // save match
//        FriendlyMatchDTO friendlyMatchDTOtoReturn = modelMapper.map(matchService.getAll().get(matchService.getAll().size() - 1), FriendlyMatchDTO.class);
//
//        return new ResponseEntity(friendlyMatchDTOtoReturn,HttpStatus.CREATED);
//        return null;
//    }
}
