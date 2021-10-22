package soccer.game.streetSoccerManager.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.model.Position;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IPositionService;

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

}
