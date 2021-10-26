package soccer.game.streetSoccerManager.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.model.entities.CustomTeam;
import soccer.game.streetSoccerManager.model.entities.Position;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IPositionService;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("/positions")
public class PositionController {
    // todo: implement controller logic
    @Qualifier("positionService")
    private IPositionService positionService;

    public PositionController(IPositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping
    public ResponseEntity<List<Position>> getAll() {
        List<Position> positions = null;

        positions = positionService.getAll();

        if(positions != null) {
            return ResponseEntity.ok().body(positions);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Position> createPosition(@RequestBody Position position) {
        if (!positionService.add(position)){
            String entity =  "Position with id " + position.getId() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "team" + "/" + position.getId(); // url of the created student
            URI uri = URI.create(url);
            return new ResponseEntity(position,HttpStatus.CREATED);
        }

    }

}
