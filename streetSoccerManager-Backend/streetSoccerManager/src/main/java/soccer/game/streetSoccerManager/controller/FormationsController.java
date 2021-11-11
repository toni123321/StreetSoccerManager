package soccer.game.streetSoccerManager.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.model.converters.FormationConverter;
import soccer.game.streetSoccerManager.model.dtos.FormationDTO;
import soccer.game.streetSoccerManager.service_interfaces.IFormationService;
import soccer.game.streetSoccerManager.model.entities.Formation;

import java.util.List;


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
    public ResponseEntity<FormationDTO> getFormation(@PathVariable(value = "id") Long id) {
        FormationDTO formation = formationService.get(id);
        if(formation != null) {
            return ResponseEntity.ok().body(formation);
        } else {
            return ResponseEntity.notFound().build();   
        }
    }

    @GetMapping
    public ResponseEntity<List<FormationDTO>> getAll() {
        List<FormationDTO> formations = formationService.getAll();
        if(formations != null) {
            return ResponseEntity.ok().body(formations);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteFormation(@PathVariable Long id) {
        formationService.delete(id);
        // Idempotent method. Always return the same response (even if the resource has already been deleted before).
        return ResponseEntity.ok().build();

    }

    @PostMapping()
    public ResponseEntity<FormationDTO> createFormation(@RequestBody FormationDTO formation) {
        FormationDTO createdFormation = formationService.add(formation);
        if (createdFormation == null){
            String msg =  "Formation with id " + formation.getId() + " already exists.";
            return new ResponseEntity(msg, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity(createdFormation,HttpStatus.CREATED);
        }
    }

    @PutMapping()
    public ResponseEntity<FormationDTO> updateFormation(@RequestBody FormationDTO formation) {
        FormationDTO updatedFormation = formationService.update(formation);
        if (updatedFormation != null) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid formation id",HttpStatus.NOT_FOUND);
        }
    }


}
