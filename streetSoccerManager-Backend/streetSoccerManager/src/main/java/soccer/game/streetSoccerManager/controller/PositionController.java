package soccer.game.streetSoccerManager.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.model.converters.PositionConverter;
import soccer.game.streetSoccerManager.model.dtos.PositionDTO;
import soccer.game.streetSoccerManager.model.entities.Position;
import soccer.game.streetSoccerManager.service_interfaces.IPositionService;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("/positions")
public class PositionController {
    // todo: implement controller logic
    @Qualifier("positionService")
    private IPositionService positionService;
    private PositionConverter positionConverter = new PositionConverter();

    public PositionController(IPositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping
    public ResponseEntity<List<PositionDTO>> getAll() {
        List<PositionDTO> positions = positionService.getAll();
        if(positions != null) {
            return ResponseEntity.ok().body(positions);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<PositionDTO> createPosition(@RequestBody PositionDTO position) {
        PositionDTO createdPosition = positionService.add(position);
        if (createdPosition == null){
            String msg =  "Position with id " + position.getId() + " already exists.";
            return new ResponseEntity(msg, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity(createdPosition,HttpStatus.CREATED);
        }

    }

}
