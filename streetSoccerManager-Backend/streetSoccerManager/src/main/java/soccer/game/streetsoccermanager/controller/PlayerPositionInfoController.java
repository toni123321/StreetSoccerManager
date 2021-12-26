package soccer.game.streetsoccermanager.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetsoccermanager.model.dtos.PlayerPositionInfoDTO;
import soccer.game.streetsoccermanager.model.entities.PlayerPositionInfo;
import soccer.game.streetsoccermanager.service_interfaces.IPlayerPositionInfoService;


import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("/playersPositionInfo")
public class PlayerPositionInfoController {

    private IPlayerPositionInfoService playerPositionInfoService;
    private ModelMapper modelMapper;

    @Autowired
    public PlayerPositionInfoController(IPlayerPositionInfoService playerPositionInfoService) {
        this.playerPositionInfoService = playerPositionInfoService;
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @GetMapping
    public ResponseEntity<List<PlayerPositionInfoDTO>> getAll() {
        List<PlayerPositionInfo> playerPositionInfoEntities = playerPositionInfoService.getAll();
        List<PlayerPositionInfoDTO> playerPositionInfoDTOs = modelMapper.map(playerPositionInfoEntities, new TypeToken<List<PlayerPositionInfoDTO>>() {}.getType());
        if(playerPositionInfoDTOs != null) {
            return ResponseEntity.ok().body(playerPositionInfoDTOs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<PlayerPositionInfoDTO> createPlayerPositionInfo(@RequestBody PlayerPositionInfoDTO playerPositionInfo) {
        PlayerPositionInfo inputtedPlayerPositionInfo = modelMapper.map(playerPositionInfo, PlayerPositionInfo.class);
        PlayerPositionInfo createdPlayerPositionInfoEntity = playerPositionInfoService.add(inputtedPlayerPositionInfo);
        PlayerPositionInfoDTO createdPlayerPositionInfoDTO = modelMapper.map(createdPlayerPositionInfoEntity, PlayerPositionInfoDTO.class);

        if (createdPlayerPositionInfoDTO == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(createdPlayerPositionInfoDTO,HttpStatus.CREATED);
        }
    }

    @PutMapping()
    public ResponseEntity<PlayerPositionInfoDTO> updatePlayerPositionInfo(@RequestBody PlayerPositionInfoDTO playerPositionInfo) {
        PlayerPositionInfo inputtedPlayerPositionInfo = modelMapper.map(playerPositionInfo, PlayerPositionInfo.class);
        PlayerPositionInfo updatedPlayerPositionInfoEntity = playerPositionInfoService.update(inputtedPlayerPositionInfo);
        PlayerPositionInfoDTO updatedPlayerPositionInfoDTO = modelMapper.map(updatedPlayerPositionInfoEntity, PlayerPositionInfoDTO.class);

        if (updatedPlayerPositionInfoDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(updatedPlayerPositionInfoDTO,HttpStatus.CREATED);
        }
    }
}
