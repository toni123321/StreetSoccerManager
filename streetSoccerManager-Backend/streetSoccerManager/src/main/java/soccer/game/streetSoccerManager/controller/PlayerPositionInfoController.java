package soccer.game.streetSoccerManager.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.model.dtos.PlayerDTO;
import soccer.game.streetSoccerManager.model.entities.Player;
import soccer.game.streetSoccerManager.model.entities.PlayerPositionInfo;
import soccer.game.streetSoccerManager.model.entities.PlayerStats;
import soccer.game.streetSoccerManager.service.PlayerPositionInfoService;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IPlayerPositionInfoService;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IPlayerStatsService;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("/playersPositionInfo")
public class PlayerPositionInfoController {
    @Qualifier("playerPositionInfoService")
    private IPlayerPositionInfoService playerPositionInfoService;

    public PlayerPositionInfoController(IPlayerPositionInfoService playerPositionInfoService) {
        this.playerPositionInfoService = playerPositionInfoService;
    }

    @GetMapping
    public ResponseEntity<List<PlayerPositionInfo>> getAll() {
        List<PlayerPositionInfo> playersPositionInfo = null;

        playersPositionInfo = playerPositionInfoService.getAll();

        if(playersPositionInfo != null) {
            return ResponseEntity.ok().body(playersPositionInfo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<PlayerPositionInfo> createPlayerPositionInfo(@RequestBody PlayerPositionInfo playerPositionInfo) {
        if (!playerPositionInfoService.add(playerPositionInfo)){
            String entity =  "PlayerPositionInfo with id " + playerPositionInfo.getId() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "playerPositionInfo" + "/" + playerPositionInfo.getId(); // url of the created student
            URI uri = URI.create(url);
            return new ResponseEntity(playerPositionInfo,HttpStatus.CREATED);
        }
    }

    @PutMapping()
    public ResponseEntity<PlayerPositionInfo> updatePlayerPositionInfo(@RequestBody PlayerPositionInfo playerPositionInfo) {
        // Idempotent method. Always update (even if the resource has already been updated before).
        //PlayerPositionInfo player = playerConverter.convertPlayerDtoToPlayer(playerDTO);
        if (playerPositionInfoService.update(playerPositionInfo)) {
            return ResponseEntity.ok().body(playerPositionInfo);
        } else {
            return new ResponseEntity("Please provide a valid playerPositionInfo id",HttpStatus.NOT_FOUND);
        }
    }
}
