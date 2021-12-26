package soccer.game.streetsoccermanager.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetsoccermanager.model.dtos.FormationDTO;
import soccer.game.streetsoccermanager.service_interfaces.IFormationService;
import soccer.game.streetsoccermanager.model.entities.Formation;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("/formations")
public class FormationsController {

    private IFormationService formationService;
    private ModelMapper modelMapper;

    @Autowired
    public FormationsController(IFormationService formationService) {
        this.formationService = formationService;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping("{id}")
    public ResponseEntity<FormationDTO> getFormation(@PathVariable(value = "id") Long id) {
        Formation formationEntity = formationService.get(id);
        FormationDTO formationDTO = modelMapper.map(formationEntity, FormationDTO.class);
        if(formationDTO != null) {
            return ResponseEntity.ok().body(formationDTO);
        } else {
            return ResponseEntity.notFound().build();   
        }
    }

    @GetMapping
    public ResponseEntity<List<FormationDTO>> getAll() {
        List<Formation> formationEntities = formationService.getAll();
        List<FormationDTO> formationDTOs = modelMapper.map(formationEntities, new TypeToken<List<FormationDTO>>() {}.getType());
        if(formationDTOs != null) {
            return ResponseEntity.ok().body(formationDTOs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteFormation(@PathVariable Long id) {
        if(Boolean.TRUE.equals(formationService.delete(id))) {
            return ResponseEntity.ok().body("Successfully deleted!");
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<FormationDTO> createFormation(@RequestBody FormationDTO formation) {
        Formation inputtedFormationEntity = modelMapper.map(formation, Formation.class);
        Formation createdFormationEntity = formationService.add(inputtedFormationEntity);
        FormationDTO createdFormationDTO = modelMapper.map(createdFormationEntity, FormationDTO.class);
        if (createdFormationDTO == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(createdFormationDTO,HttpStatus.CREATED);
        }
    }

    @PutMapping()
    public ResponseEntity<FormationDTO> updateFormation(@RequestBody FormationDTO formation) {
        Formation inputtedFormationEntity = modelMapper.map(formation, Formation.class);
        Formation updatedFormationEntity = formationService.update(inputtedFormationEntity);
        FormationDTO updatedFormationDTO = modelMapper.map(updatedFormationEntity, FormationDTO.class);
        if (updatedFormationDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(updatedFormationDTO,HttpStatus.CREATED);
        }
    }


}
