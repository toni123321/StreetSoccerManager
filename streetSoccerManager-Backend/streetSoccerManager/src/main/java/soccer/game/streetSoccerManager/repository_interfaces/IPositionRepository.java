package soccer.game.streetSoccerManager.repository_interfaces;


import soccer.game.streetSoccerManager.model.entities.Position;

import java.util.List;

public interface IPositionRepository {
    List<Position> getAll();
    Position get(Long id);
    Position get(String searchedPosition);
    Boolean delete(Long id);
    Position add(Position position);
    Position update(Position position);
    void deleteAll();
}
