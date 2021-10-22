package soccer.game.street_soccer_manager.service.serviceInterfaces;

import soccer.game.street_soccer_manager.model.Position;

import java.util.List;

public interface IPositionService {
    List<Position> getAll();
    Position get(Long id);
    Boolean delete(Long id);
    Boolean add(Position position);
    Boolean update(Position position);
}
