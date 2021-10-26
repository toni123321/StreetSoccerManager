package soccer.game.streetSoccerManager.repository.repositoryInterfaces;


import soccer.game.streetSoccerManager.model.entities.Position;

import java.util.List;

public interface IPositionRepository {
    List<Position> getAll();
    Position get(Long id);
    Position get(String searchedPosition);
    Boolean delete(Long id);
    Boolean add(Position position);
    Boolean update(Position position);
}
