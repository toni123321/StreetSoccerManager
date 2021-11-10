package soccer.game.streetSoccerManager.service_interfaces;

import soccer.game.streetSoccerManager.model.entities.Position;


import java.util.List;

public interface IPositionService {
    List<Position> getAll();
    Position get(Long id);
    Boolean delete(Long id);
    Boolean add(Position position);
    Boolean update(Position position);
}
