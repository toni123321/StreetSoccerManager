package soccer.game.streetSoccerManager.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.model.dtos.FormationDTO;
import soccer.game.streetSoccerManager.model.dtos.PlayerStatsDTO;
import soccer.game.streetSoccerManager.model.entities.PlayerStats;
import soccer.game.streetSoccerManager.service_interfaces.IPlayerStatsService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("/playerStats")
public class PlayerStatsController {

    @Qualifier("playerStatsService")
    private IPlayerStatsService playerStatsService;
    private ModelMapper modelMapper;

    public PlayerStatsController(IPlayerStatsService playerStatsService) {
        this.playerStatsService = playerStatsService;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping("{id}")
    public ResponseEntity<PlayerStatsDTO> getPlayerStats(@PathVariable(value = "id") Long id) {
        PlayerStats playerStatsEntity = playerStatsService.get(id);
        PlayerStatsDTO playerStatsDTO = modelMapper.map(playerStatsEntity, PlayerStatsDTO.class);
        if(playerStatsDTO != null) {
            return ResponseEntity.ok().body(playerStatsDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<PlayerStatsDTO>> getAll() {
        List<PlayerStats> playerStatsEntities = playerStatsService.getAll();
        List<PlayerStatsDTO> playerStatsDTOs = modelMapper.map(playerStatsEntities, new TypeToken<List<PlayerStatsDTO>>() {}.getType());
        if(playerStatsDTOs != null) {
            return ResponseEntity.ok().body(playerStatsDTOs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletePlayerStats(@PathVariable Long id) {
        if(Boolean.TRUE.equals(playerStatsService.delete(id))) {
            return ResponseEntity.ok().body("Successfully deleted!");
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<PlayerStatsDTO> createPlayerStats(@RequestBody PlayerStatsDTO playerStats) {
        PlayerStats inputtedPlayerStatsEntity = modelMapper.map(playerStats, PlayerStats.class);
        PlayerStats createdPlayerStatsEntity = playerStatsService.add(inputtedPlayerStatsEntity);
        PlayerStatsDTO createdPlayerStatsDTO = modelMapper.map(createdPlayerStatsEntity, PlayerStatsDTO.class);
        if (createdPlayerStatsDTO == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(createdPlayerStatsDTO,HttpStatus.CREATED);
        }
    }

    @PutMapping()
    public ResponseEntity<PlayerStatsDTO> updatePlayerStats(@RequestBody PlayerStatsDTO playerStats) {
        PlayerStats inputtedPlayerStatsEntity = modelMapper.map(playerStats, PlayerStats.class);
        PlayerStats updatedPlayerStatsEntity = playerStatsService.update(inputtedPlayerStatsEntity);
        PlayerStatsDTO updatedPlayerStatsDTO = modelMapper.map(updatedPlayerStatsEntity, PlayerStatsDTO.class);
        if (updatedPlayerStatsDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(updatedPlayerStatsDTO,HttpStatus.CREATED);
        }
    }
}
