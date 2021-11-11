package soccer.game.streetSoccerManager.service_interfaces;

import soccer.game.streetSoccerManager.model.dtos.PositionDTO;
import soccer.game.streetSoccerManager.model.entities.Position;


import java.util.List;

public interface IPositionService {
    List<PositionDTO> getAll();
    PositionDTO get(Long id);
    Boolean delete(Long id);
    PositionDTO add(PositionDTO position);
    PositionDTO update(PositionDTO position);
}
