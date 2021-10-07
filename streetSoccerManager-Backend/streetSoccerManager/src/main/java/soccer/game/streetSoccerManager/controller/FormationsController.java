package soccer.game.streetSoccerManager.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.interfaces.serviceInterfaces.IFormationService;
import soccer.game.streetSoccerManager.interfaces.serviceInterfaces.ITeamService;
import soccer.game.streetSoccerManager.interfaces.serviceInterfaces.IUserService;
import soccer.game.streetSoccerManager.model.Formation;
import soccer.game.streetSoccerManager.model.FormationPosition;
import soccer.game.streetSoccerManager.model.Team;
import soccer.game.streetSoccerManager.model.User;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("/formations")
public class FormationsController {
    @Qualifier("formationService")
    private IFormationService formationService;

    public FormationsController(IFormationService formationService) {
        this.formationService = formationService;
    }



    @GetMapping("{id}")
    public ResponseEntity<Formation> getFormation(@PathVariable(value = "id") int id) {
        Formation formation = formationService.get(id);

        if(formation != null) {
            return ResponseEntity.ok().body(formation);
        } else {
            return ResponseEntity.notFound().build();   
        }
    }


    @GetMapping
    public ResponseEntity<List<Formation>> getAll() {
        List<Formation> formations = null;

        formations = formationService.getAll();

        if(formations != null) {
            return ResponseEntity.ok().body(formations);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity deleteFormation(@PathVariable int id) {
        formationService.delete(id);
        // Idempotent method. Always return the same response (even if the resource has already been deleted before).
        return ResponseEntity.ok().build();

    }

    @PostMapping()
    public ResponseEntity<Formation> createFormation(@RequestBody Formation formation) {
        if (!formationService.add(formation)){
            String entity =  "Formation with id " + formation.getId() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
           /* String url = "team" + "/" + team.getId(); // url of the created student
            URI uri = URI.create(url);*/
            return new ResponseEntity(formation,HttpStatus.CREATED);
        }

    }

    @PutMapping()
    public ResponseEntity<Formation> updateFormation(@RequestBody Formation formation) {
        // Idempotent method. Always update (even if the resource has already been updated before).
        if (formationService.update(formation)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid formation id",HttpStatus.NOT_FOUND);
        }
    }


}
