package soccer.game.streetsoccermanager.service_interfaces;

import soccer.game.streetsoccermanager.model.entities.Position;


import java.util.List;

public interface IPositionService {
    List<Position> getAll();
    Position get(Long id);
    Boolean delete(Long id);
    Position add(Position position);
    Position update(Position position);
    void deleteAll();
}
