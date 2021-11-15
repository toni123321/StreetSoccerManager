package soccer.game.streetSoccerManager.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.model.converters.PlayerPositionInfoConverter;
import soccer.game.streetSoccerManager.model.dtos.PlayerPositionInfoDTO;
import soccer.game.streetSoccerManager.model.entities.PlayerPositionInfo;
import soccer.game.streetSoccerManager.service_interfaces.IPlayerPositionInfoService;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("/playersPositionInfo")
public class PlayerPositionInfoController {
    @Qualifier("playerPositionInfoService")
    private IPlayerPositionInfoService playerPositionInfoService;
    private PlayerPositionInfoConverter playerPositionInfoConverter = new PlayerPositionInfoConverter();

    public PlayerPositionInfoController(IPlayerPositionInfoService playerPositionInfoService) {
        this.playerPositionInfoService = playerPositionInfoService;
    }

    @GetMapping
    public ResponseEntity<List<PlayerPositionInfoDTO>> getAll() {
        List<PlayerPositionInfoDTO> playersPositionInfoDTO = playerPositionInfoService.getAll();

        if(playersPositionInfoDTO != null) {
            return ResponseEntity.ok().body(playersPositionInfoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<PlayerPositionInfoDTO> createPlayerPositionInfo(@RequestBody PlayerPositionInfoDTO playerPositionInfo) {
        PlayerPositionInfoDTO createdPlayerPositionInfo = playerPositionInfoService.add(playerPositionInfo);
        if (createdPlayerPositionInfo == null){
            String entity =  "PlayerPositionInfo with id " + playerPositionInfo.getId() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity(createdPlayerPositionInfo,HttpStatus.CREATED);
        }
    }

    @PutMapping()
    public ResponseEntity<PlayerPositionInfoDTO> updatePlayerPositionInfo(@RequestBody PlayerPositionInfoDTO playerPositionInfo) {
        PlayerPositionInfoDTO updatedPlayerPositionInfo = playerPositionInfoService.update(playerPositionInfo);
        if (updatedPlayerPositionInfo != null) {
            return ResponseEntity.ok().body(updatedPlayerPositionInfo);
        } else {
            return new ResponseEntity("Please provide a valid playerPositionInfo id",HttpStatus.NOT_FOUND);
        }
    }
}
