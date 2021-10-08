package soccer.game.streetSoccerManager.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.interfaces.serviceInterfaces.IFormationPositionService;
import soccer.game.streetSoccerManager.interfaces.serviceInterfaces.IFormationService;
import soccer.game.streetSoccerManager.model.Formation;
import soccer.game.streetSoccerManager.model.FormationPosition;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("/formationPositions")
public class FormationPositionController {
    @Qualifier("formationPositionService")
    private IFormationPositionService formationPositionService;

    public FormationPositionController(IFormationPositionService formationPositionService) {
        this.formationPositionService = formationPositionService;
    }



    @GetMapping("{id}")
    public ResponseEntity<FormationPosition> getPosition(@PathVariable(value = "id") int id) {
        FormationPosition position = formationPositionService.get(id);

        if(position != null) {
            return ResponseEntity.ok().body(position);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<FormationPosition>> getAll(
            @RequestParam(value = "teamId") Optional<Integer> teamId,
            @RequestParam(value = "formationId") Optional<Integer> formationId,
            @RequestParam(value = "starting") Optional<Boolean> starting)
    {
        List<FormationPosition> positions = null;

        if(formationId.isPresent() && teamId.isPresent())
        {
            if(starting.isPresent())
            {
                if(starting.get())
                {
                    positions = formationPositionService.
                            getStartingPositionsByTeamAndFormation(teamId.get(), formationId.get());
                }
                else{
                    positions = formationPositionService.
                            getPositionsForReservesByTeamAndFormation(teamId.get(), formationId.get());
                }
            }
            else {
                positions = formationPositionService.getAllPositionsByTeamAndFormation(teamId.get(), formationId.get());
            }
        }
        else {
            positions = formationPositionService.getAll();
        }

        if(positions != null) {
            return ResponseEntity.ok().body(positions);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletePosition(@PathVariable int id) {
        formationPositionService.delete(id);
        // Idempotent method. Always return the same response (even if the resource has already been deleted before).
        return ResponseEntity.ok().build();

    }

    @PostMapping()
    public ResponseEntity<FormationPosition> createPosition(@RequestBody FormationPosition position) {
        if (!formationPositionService.add(position)){
            String entity =  "Position with id " + position.getId() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
           /* String url = "team" + "/" + team.getId(); // url of the created student
            URI uri = URI.create(url);*/
            return new ResponseEntity(position,HttpStatus.CREATED);
        }

    }

    @PutMapping()
    public ResponseEntity<Formation> updatePosition(@RequestBody FormationPosition position) {
        // Idempotent method. Always update (even if the resource has already been updated before).
        if (formationPositionService.update(position)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid position id",HttpStatus.NOT_FOUND);
        }
    }

}
