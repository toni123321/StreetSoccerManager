package soccer.game.streetSoccerManager.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.model.dtos.PositionDTO;
import soccer.game.streetSoccerManager.model.entities.Position;
import soccer.game.streetSoccerManager.service_interfaces.IPositionService;


import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("/positions")
public class PositionController {
    @Qualifier("positionService")
    private IPositionService positionService;
    private ModelMapper modelMapper;

    public PositionController(IPositionService positionService) {
        this.positionService = positionService;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping
    public ResponseEntity<List<PositionDTO>> getAll() {
        List<Position> positionEntities = positionService.getAll();
        List<PositionDTO> positionDTOs = modelMapper.map(positionEntities, new TypeToken<List<PositionDTO>>() {}.getType());
        if(positionDTOs != null) {
            return ResponseEntity.ok().body(positionDTOs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<PositionDTO> getPosition(@PathVariable(value = "id") Long id) {
        Position positionEntity = positionService.get(id);
        PositionDTO positionDTO = modelMapper.map(positionEntity, PositionDTO.class);
        if(positionDTO != null) {
            return ResponseEntity.ok().body(positionDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePosition(@PathVariable Long id) {
        if(Boolean.TRUE.equals(positionService.delete(id))) {
            return ResponseEntity.ok().body("Successfully deleted!");
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<PositionDTO> createPosition(@RequestBody PositionDTO position) {
        Position inputtedPositionEntity = modelMapper.map(position, Position.class);
        Position createdPositionEntity = positionService.add(inputtedPositionEntity);
        PositionDTO createdPositionDTO = modelMapper.map(createdPositionEntity, PositionDTO.class);
        if (createdPositionDTO == null){
            String msg =  "Position with id " + position.getId() + " already exists.";
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(createdPositionDTO,HttpStatus.CREATED);
        }

    }

    @PutMapping()
    public ResponseEntity<PositionDTO> updatePosition(@RequestBody PositionDTO position) {
        Position inputtedPositionEntity = modelMapper.map(position, Position.class);
        Position updatedPositionEntity = positionService.update(inputtedPositionEntity);
        PositionDTO updatedPositionDTO = modelMapper.map(updatedPositionEntity, PositionDTO.class);
        if (updatedPositionDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(updatedPositionDTO, HttpStatus.OK);
        }

    }

}
